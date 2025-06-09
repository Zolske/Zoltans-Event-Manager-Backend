--
-- PostgreSQL database dump
--

-- Dumped from database version 16.9 (Ubuntu 16.9-0ubuntu0.24.04.1)
-- Dumped by pg_dump version 16.9 (Ubuntu 16.9-0ubuntu0.24.04.1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: app_user; Type: TABLE; Schema: public; Owner: zoli
--

CREATE TABLE public.app_user (
    id_user text NOT NULL,
    name text NOT NULL,
    email text,
    picture_url text,
    is_admin boolean DEFAULT false,
    is_root_admin boolean DEFAULT false
);


ALTER TABLE public.app_user OWNER TO zoli;

--
-- Name: events; Type: TABLE; Schema: public; Owner: zoli
--

CREATE TABLE public.events (
    id_event integer NOT NULL,
    description_short text NOT NULL,
    description text NOT NULL,
    date date NOT NULL,
    "time" time with time zone NOT NULL,
    address text NOT NULL,
    is_over boolean,
    title text NOT NULL
);


ALTER TABLE public.events OWNER TO zoli;

--
-- Name: event_id_event_seq; Type: SEQUENCE; Schema: public; Owner: zoli
--

CREATE SEQUENCE public.event_id_event_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.event_id_event_seq OWNER TO zoli;

--
-- Name: event_id_event_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zoli
--

ALTER SEQUENCE public.event_id_event_seq OWNED BY public.events.id_event;


--
-- Name: subscription; Type: TABLE; Schema: public; Owner: zoli
--

CREATE TABLE public.subscription (
    fk_id_user text,
    fk_id_event integer,
    id_subscription integer NOT NULL
);


ALTER TABLE public.subscription OWNER TO zoli;

--
-- Name: TABLE subscription; Type: COMMENT; Schema: public; Owner: zoli
--

COMMENT ON TABLE public.subscription IS 'Containing the users with there subscribed events.';


--
-- Name: subscriber_id_subscriber_seq; Type: SEQUENCE; Schema: public; Owner: zoli
--

CREATE SEQUENCE public.subscriber_id_subscriber_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.subscriber_id_subscriber_seq OWNER TO zoli;

--
-- Name: subscriber_id_subscriber_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zoli
--

ALTER SEQUENCE public.subscriber_id_subscriber_seq OWNED BY public.subscription.id_subscription;


--
-- Name: events id_event; Type: DEFAULT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.events ALTER COLUMN id_event SET DEFAULT nextval('public.event_id_event_seq'::regclass);


--
-- Name: subscription id_subscription; Type: DEFAULT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.subscription ALTER COLUMN id_subscription SET DEFAULT nextval('public.subscriber_id_subscriber_seq'::regclass);


--
-- Name: events event_pkey; Type: CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT event_pkey PRIMARY KEY (id_event);


--
-- Name: subscription subscriber_pkey; Type: CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.subscription
    ADD CONSTRAINT subscriber_pkey PRIMARY KEY (id_subscription);


--
-- Name: app_user user_pkey; Type: CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT user_pkey PRIMARY KEY (id_user);


--
-- Name: subscription subscriber_fk_id_event_fkey; Type: FK CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.subscription
    ADD CONSTRAINT subscriber_fk_id_event_fkey FOREIGN KEY (fk_id_event) REFERENCES public.events(id_event) NOT VALID;


--
-- Name: subscription subscriber_fk_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.subscription
    ADD CONSTRAINT subscriber_fk_id_user_fkey FOREIGN KEY (fk_id_user) REFERENCES public.app_user(id_user) NOT VALID;


--
-- PostgreSQL database dump complete
--

