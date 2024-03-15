--
-- PostgreSQL database dump
--

-- Dumped from database version 14.11 (Ubuntu 14.11-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.11 (Ubuntu 14.11-0ubuntu0.22.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: add_type_most_money_spent(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.add_type_most_money_spent() RETURNS text
    LANGUAGE plpgsql
    AS $_$
    declare
        results text;
    begin
      SELECT json_build_object(
           'add_type', a.add_type,
           'total_expense_$', SUM(a."add_expense_$")
       ) into results
FROM advertising a
GROUP BY a.add_type
ORDER BY SUM(a."add_expense_$") DESC
LIMIT 1;

        return results;
    end;
    $_$;


ALTER FUNCTION public.add_type_most_money_spent() OWNER TO postgres;

--
-- Name: count_add_stopped_within_last_month(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.count_add_stopped_within_last_month() RETURNS integer
    LANGUAGE plpgsql
    AS $$
    DECLARE
    count_ads INT;
BEGIN
    SELECT COUNT(id)
    INTO count_ads
    FROM advertising
    WHERE add_period::date > CURRENT_DATE - INTERVAL '1' MONTH
      AND add_period::date < CURRENT_DATE;

    RETURN count_ads;
    end;
    $$;


ALTER FUNCTION public.count_add_stopped_within_last_month() OWNER TO postgres;

--
-- Name: count_add_within_last_month(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.count_add_within_last_month() RETURNS integer
    LANGUAGE plpgsql
    AS $$
    declare
        count int;
    begin
SELECT COUNT(id)
into count
FROM advertising
WHERE add_begin_time::date >= CURRENT_DATE - INTERVAL '1' MONTH;
return count;
    end;
    $$;


ALTER FUNCTION public.count_add_within_last_month() OWNER TO postgres;

--
-- Name: get_client_info(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.get_client_info() RETURNS text
    LANGUAGE plpgsql
    AS $$
DECLARE
    result TEXT;
BEGIN
    SELECT json_agg(
            json_build_object(
                'date', c.date,
                'client_count', c.client_count
            )
        )
    INTO result
    from (select e.date,count(*) as client_count from client e group by date
         ) c;

    RETURN result;
END;
$$;


ALTER FUNCTION public.get_client_info() OWNER TO postgres;

--
-- Name: get_clients_in_last_month(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.get_clients_in_last_month() RETURNS integer
    LANGUAGE plpgsql
    AS $$
    declare
        count int;
    begin
select count(*)
into count
FROM client
WHERE date::date >= CURRENT_DATE - INTERVAL '1 month'
  AND date::date <= CURRENT_DATE;

return count;
end;
    $$;


ALTER FUNCTION public.get_clients_in_last_month() OWNER TO postgres;

--
-- Name: get_department_info(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.get_department_info() RETURNS text
    LANGUAGE plpgsql
    AS $$
DECLARE
    result TEXT;
BEGIN
    SELECT json_agg(
            json_build_object(
                'department_name', d.department_name,
                'employee_count', e.employee_count
            )
        )
    INTO result
    FROM department d
    INNER JOIN (
        SELECT department_id, COUNT(*) AS employee_count
        FROM employee
        GROUP BY department_id
    ) e ON d.id = e.department_id;

    RETURN result;
END;
$$;


ALTER FUNCTION public.get_department_info() OWNER TO postgres;

--
-- Name: get_employee_spent_more_money(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.get_employee_spent_more_money() RETURNS text
    LANGUAGE plpgsql
    AS $_$
    declare
        result text;
    begin

       SELECT json_agg(
           json_build_object(
               'id', a.id,
               'account', a.account,
               'max_amount', a.max_amount
           )
       )
       into result
FROM (
    SELECT a.id, a.account, MAX(ad."add_expense_$") AS max_amount
    FROM account a
    JOIN advertising ad ON a.id = ad.employee_account_id
    WHERE ad."add_expense_$" = (
        SELECT MAX("add_expense_$")
        FROM advertising
    )
    GROUP BY a.id, a.account
) AS a;
        return result;
    end;
    $_$;


ALTER FUNCTION public.get_employee_spent_more_money() OWNER TO postgres;

--
-- Name: top_3_employees(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.top_3_employees() RETURNS text
    LANGUAGE plpgsql
    AS $$
DECLARE
    result TEXT;
BEGIN
    SELECT json_agg(
            json_build_object(
                'id', id,
                'account', account,
                'client_count', client_count
            )
        )
    INTO result
    FROM (
        SELECT a.id, a.account, COUNT(c.employee_account_id) AS client_count
        FROM account a
        LEFT JOIN client c ON a.id = c.employee_account_id
        WHERE a.id IN (
            SELECT employee_account_id
            FROM client
            GROUP BY employee_account_id
            ORDER BY COUNT(*) DESC
            LIMIT 3
        )
        GROUP BY a.id, a.account
        ORDER BY client_count DESC
        LIMIT 3
    ) AS subquery;

    RETURN result;
END;
$$;


ALTER FUNCTION public.top_3_employees() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account (
    id bigint NOT NULL,
    account character varying(255)
);


ALTER TABLE public.account OWNER TO postgres;

--
-- Name: account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.account_id_seq OWNER TO postgres;

--
-- Name: account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.account_id_seq OWNED BY public.account.id;


--
-- Name: advertising; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.advertising (
    id bigint NOT NULL,
    add_begin_time character varying(255),
    "add_expense_$" double precision,
    add_period character varying(255),
    add_type character varying(255),
    employee_account_id bigint
);


ALTER TABLE public.advertising OWNER TO postgres;

--
-- Name: advertising_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.advertising_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.advertising_id_seq OWNER TO postgres;

--
-- Name: advertising_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.advertising_id_seq OWNED BY public.advertising.id;


--
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    id bigint NOT NULL,
    address character varying(255) NOT NULL,
    client_first_name character varying(255),
    client_last_name character varying(255),
    date character varying(255),
    jshshir character varying(255) NOT NULL,
    passport_number character varying(255) NOT NULL,
    passport_series character varying(255) NOT NULL,
    employee_account_id bigint
);


ALTER TABLE public.client OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_id_seq OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.client_id_seq OWNED BY public.client.id;


--
-- Name: department; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.department (
    id bigint NOT NULL,
    department_name character varying(255)
);


ALTER TABLE public.department OWNER TO postgres;

--
-- Name: department_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.department_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.department_id_seq OWNER TO postgres;

--
-- Name: department_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.department_id_seq OWNED BY public.department.id;


--
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee (
    id bigint NOT NULL,
    address character varying(255) NOT NULL,
    age integer NOT NULL,
    first_name character varying(255),
    jshshir character varying(255) NOT NULL,
    last_name character varying(255),
    nation character varying(255),
    passport_number character varying(255) NOT NULL,
    passport_series character varying(255) NOT NULL,
    salary double precision NOT NULL,
    account_id bigint NOT NULL,
    department_id bigint,
    position_role_name character varying(255)
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- Name: employee_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employee_id_seq OWNER TO postgres;

--
-- Name: employee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.employee_id_seq OWNED BY public.employee.id;


--
-- Name: user_entity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_entity (
    id bigint NOT NULL,
    password character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    employee_id bigint NOT NULL,
    position_role_name character varying(255)
);


ALTER TABLE public.user_entity OWNER TO postgres;

--
-- Name: user_entity_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_entity_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_entity_id_seq OWNER TO postgres;

--
-- Name: user_entity_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_entity_id_seq OWNED BY public.user_entity.id;


--
-- Name: user_position_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_position_role (
    name character varying(255) NOT NULL
);


ALTER TABLE public.user_position_role OWNER TO postgres;

--
-- Name: account id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account ALTER COLUMN id SET DEFAULT nextval('public.account_id_seq'::regclass);


--
-- Name: advertising id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.advertising ALTER COLUMN id SET DEFAULT nextval('public.advertising_id_seq'::regclass);


--
-- Name: client id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client ALTER COLUMN id SET DEFAULT nextval('public.client_id_seq'::regclass);


--
-- Name: department id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department ALTER COLUMN id SET DEFAULT nextval('public.department_id_seq'::regclass);


--
-- Name: employee id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee ALTER COLUMN id SET DEFAULT nextval('public.employee_id_seq'::regclass);


--
-- Name: user_entity id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_entity ALTER COLUMN id SET DEFAULT nextval('public.user_entity_id_seq'::regclass);


--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.account (id, account) FROM stdin;
1	islom@gmail.com
2	umid@gmail.com
3	aziz@gmail.com
4	laziz@gmail.com
5	uyhjnbn@gmail.com
\.


--
-- Data for Name: advertising; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.advertising (id, add_begin_time, "add_expense_$", add_period, add_type, employee_account_id) FROM stdin;
\.


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.client (id, address, client_first_name, client_last_name, date, jshshir, passport_number, passport_series, employee_account_id) FROM stdin;
\.


--
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.department (id, department_name) FROM stdin;
1	Sales
2	Client Service
3	IT Service
\.


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employee (id, address, age, first_name, jshshir, last_name, nation, passport_number, passport_series, salary, account_id, department_id, position_role_name) FROM stdin;
2	amir temur 117	25	umid	$2a$10$Xz0dBtjDfOAX8QGym.a7sObD1pc3J1jJqIyVjXujLe09WbiN4Fita	umarov	uzbek	$2a$10$PlkD4m/piXu9FcKG7I2f0eL26BnAAyRzF62TAJnFgKCnJUuIuU0P2	AC	1700	2	1	DIRECTOR
3	amir temur 117	24	aziz	$2a$10$sGLnZ67iVJu8J60bc.O1ZO9RkKAecviX0mX/4rMurssTIwDgBI3FW	alimov	uzbek	$2a$10$bOOOZhumMjTuhHDEZvTtqe32OEL7ziRvkbmM.9gxh4jyMfq8lff.m	AC	1700	3	1	HEAD_OF_DEPARTMENT
8	amir temur 117	24	islom	$2a$10$LiOZT6iw8dQ9d3siHsFNRetECYP4.2TWaoqzrEoHAgAbNzgjDeQX.	alimov	uzbek	$2a$10$bBJtl6Ea2Vo4R164pZI1Le/45nxdhoZswQ0Ty1XWIYqIZaWmPc6wC	AC	1700	1	1	EMPLOYEE
\.


--
-- Data for Name: user_entity; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_entity (id, password, username, employee_id, position_role_name) FROM stdin;
10	$2a$10$AVCoNtsjFFredJmqdMWa7uBq6IxWJulAJzJpKlSL/QAORqWwy4SlS	usernam	3	HEAD_OF_DEPARTMENT
11	$2a$10$nkEb02j1KCxYG1NtQaK5lOEEpfndmuurcfEGpdm2S.5pu69xaGk0e	umidjon	2	DIRECTOR
13	$2a$10$RjqNYfe3UZg9YoYcDZ4RVuJLeEXN6Shkc/9XYgJltDAWBJwFyaYjK	islomjon	8	EMPLOYEE
\.


--
-- Data for Name: user_position_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_position_role (name) FROM stdin;
EMPLOYEE
HEAD_OF_DEPARTMENT
DIRECTOR
\.


--
-- Name: account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.account_id_seq', 5, true);


--
-- Name: advertising_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.advertising_id_seq', 1, false);


--
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.client_id_seq', 1, false);


--
-- Name: department_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.department_id_seq', 3, true);


--
-- Name: employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employee_id_seq', 8, true);


--
-- Name: user_entity_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_entity_id_seq', 13, true);


--
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- Name: advertising advertising_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.advertising
    ADD CONSTRAINT advertising_pkey PRIMARY KEY (id);


--
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- Name: department department_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT department_pkey PRIMARY KEY (id);


--
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);


--
-- Name: employee uk_1indr66x62jgil0qfu2xrfrgu; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT uk_1indr66x62jgil0qfu2xrfrgu UNIQUE (passport_number);


--
-- Name: user_entity uk_2jsk4eakd0rmvybo409wgwxuw; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_entity
    ADD CONSTRAINT uk_2jsk4eakd0rmvybo409wgwxuw UNIQUE (username);


--
-- Name: client uk_3yetfjp8xev02wqgl44h90v38; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT uk_3yetfjp8xev02wqgl44h90v38 UNIQUE (passport_number);


--
-- Name: account uk_gj03dh42inu2jgv2077dg5eqo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT uk_gj03dh42inu2jgv2077dg5eqo UNIQUE (account);


--
-- Name: client uk_jd3ydn4qcv64chwj8ohx9f2yu; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT uk_jd3ydn4qcv64chwj8ohx9f2yu UNIQUE (jshshir);


--
-- Name: employee uk_lsnx7na4u8ohrhoeag7un4wh3; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT uk_lsnx7na4u8ohrhoeag7un4wh3 UNIQUE (account_id);


--
-- Name: user_entity uk_rvep2fg3w9bl228g4f8yapio7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_entity
    ADD CONSTRAINT uk_rvep2fg3w9bl228g4f8yapio7 UNIQUE (employee_id);


--
-- Name: user_entity uk_sgucyq425oe4apv5jqhbfvdbe; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_entity
    ADD CONSTRAINT uk_sgucyq425oe4apv5jqhbfvdbe UNIQUE (password);


--
-- Name: employee uk_tg710u8y00qc3d2gsfh3ky8lj; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT uk_tg710u8y00qc3d2gsfh3ky8lj UNIQUE (jshshir);


--
-- Name: user_entity user_entity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_entity
    ADD CONSTRAINT user_entity_pkey PRIMARY KEY (id);


--
-- Name: user_position_role user_position_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_position_role
    ADD CONSTRAINT user_position_role_pkey PRIMARY KEY (name);


--
-- Name: employee fk7oq5wqpkqd7hajjdsjyfsiujg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fk7oq5wqpkqd7hajjdsjyfsiujg FOREIGN KEY (position_role_name) REFERENCES public.user_position_role(name);


--
-- Name: client fkb4vsnu7b57g0wah62f2i14ler; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT fkb4vsnu7b57g0wah62f2i14ler FOREIGN KEY (employee_account_id) REFERENCES public.account(id);


--
-- Name: employee fkbejtwvg9bxus2mffsm3swj3u9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fkbejtwvg9bxus2mffsm3swj3u9 FOREIGN KEY (department_id) REFERENCES public.department(id);


--
-- Name: employee fkcfg6ajo8oske94exynxpf7tf9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fkcfg6ajo8oske94exynxpf7tf9 FOREIGN KEY (account_id) REFERENCES public.account(id);


--
-- Name: user_entity fkeam2esk9wbw0d1tikgw0vpqvy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_entity
    ADD CONSTRAINT fkeam2esk9wbw0d1tikgw0vpqvy FOREIGN KEY (position_role_name) REFERENCES public.user_position_role(name);


--
-- Name: advertising fkgfdmg3c4pnbs15pmsobhr5r15; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.advertising
    ADD CONSTRAINT fkgfdmg3c4pnbs15pmsobhr5r15 FOREIGN KEY (employee_account_id) REFERENCES public.account(id);


--
-- Name: user_entity foreign_key; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_entity
    ADD CONSTRAINT foreign_key FOREIGN KEY (employee_id) REFERENCES public.employee(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

