--
-- PostgreSQL database dump
--


-- Dumped from database version 17.10
-- Dumped by pg_dump version 17.10

-- Started on 2026-05-25 13:33:04

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- TOC entry 220 (class 1259 OID 16397)
-- Name: cargo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cargo (
    id_cargo integer NOT NULL,
    denominacion character varying(100) NOT NULL,
    codigo character varying(20) NOT NULL,
    grado integer NOT NULL,
    id_dependencia integer
);


ALTER TABLE public.cargo OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16396)
-- Name: cargo_id_cargo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cargo_id_cargo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cargo_id_cargo_seq OWNER TO postgres;

--
-- TOC entry 4983 (class 0 OID 0)
-- Dependencies: 219
-- Name: cargo_id_cargo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cargo_id_cargo_seq OWNED BY public.cargo.id_cargo;


--
-- TOC entry 218 (class 1259 OID 16390)
-- Name: dependencia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dependencia (
    id_dependencia integer NOT NULL,
    nombre_dependencia character varying(100) NOT NULL,
    nivel character varying(50)
);


ALTER TABLE public.dependencia OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16389)
-- Name: dependencia_id_dependencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.dependencia_id_dependencia_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.dependencia_id_dependencia_seq OWNER TO postgres;

--
-- TOC entry 4984 (class 0 OID 0)
-- Dependencies: 217
-- Name: dependencia_id_dependencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.dependencia_id_dependencia_seq OWNED BY public.dependencia.id_dependencia;


--
-- TOC entry 228 (class 1259 OID 16457)
-- Name: licencia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.licencia (
    id_licencia integer NOT NULL,
    id_servidor integer,
    tipo_licencia character varying(50) NOT NULL,
    fecha_inicio date NOT NULL,
    fecha_fin date NOT NULL
);


ALTER TABLE public.licencia OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16456)
-- Name: licencia_id_licencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.licencia_id_licencia_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.licencia_id_licencia_seq OWNER TO postgres;

--
-- TOC entry 4985 (class 0 OID 0)
-- Dependencies: 227
-- Name: licencia_id_licencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.licencia_id_licencia_seq OWNED BY public.licencia.id_licencia;


--
-- TOC entry 226 (class 1259 OID 16443)
-- Name: permiso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permiso (
    id_permiso integer NOT NULL,
    id_servidor integer,
    tipo_permiso character varying(50) NOT NULL,
    fecha date NOT NULL,
    justificacion text,
    num_dias integer
);


ALTER TABLE public.permiso OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16442)
-- Name: permiso_id_permiso_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.permiso_id_permiso_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.permiso_id_permiso_seq OWNER TO postgres;

--
-- TOC entry 4986 (class 0 OID 0)
-- Dependencies: 225
-- Name: permiso_id_permiso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.permiso_id_permiso_seq OWNED BY public.permiso.id_permiso;


--
-- TOC entry 222 (class 1259 OID 16409)
-- Name: servidor_publico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.servidor_publico (
    id_servidor integer NOT NULL,
    cedula character varying(15) NOT NULL,
    sexo character(1),
    tipo_vinculacion character varying(50) NOT NULL,
    correo_institucional character varying(100),
    id_cargo integer,
    id_dependencia integer,
    nombres character varying(100),
    apellidos character varying(100),
    fecha_nacimiento date,
    estado_civil character varying(30),
    grupo_sanguineo character varying(5),
    correo_personal character varying(100),
    telefono character varying(20),
    CONSTRAINT servidor_publico_genero_check CHECK ((sexo = ANY (ARRAY['M'::bpchar, 'F'::bpchar])))
);


ALTER TABLE public.servidor_publico OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16408)
-- Name: servidor_publico_id_servidor_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.servidor_publico_id_servidor_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.servidor_publico_id_servidor_seq OWNER TO postgres;

--
-- TOC entry 4987 (class 0 OID 0)
-- Dependencies: 221
-- Name: servidor_publico_id_servidor_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.servidor_publico_id_servidor_seq OWNED BY public.servidor_publico.id_servidor;


--
-- TOC entry 232 (class 1259 OID 16515)
-- Name: situacion_administrativa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.situacion_administrativa (
    id_situacion integer NOT NULL,
    id_servidor integer NOT NULL,
    tipo_situacion character varying(30) NOT NULL,
    fecha_inicio date NOT NULL,
    fecha_fin date,
    descripcion text,
    id_acto_administrativo integer,
    CONSTRAINT chk_tipo_situacion CHECK (((tipo_situacion)::text = ANY ((ARRAY['vacaciones'::character varying, 'permiso'::character varying, 'licencia'::character varying, 'encargo'::character varying, 'traslado'::character varying, 'comision'::character varying])::text[])))
);


ALTER TABLE public.situacion_administrativa OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 16514)
-- Name: situacion_administrativa_id_situacion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.situacion_administrativa_id_situacion_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.situacion_administrativa_id_situacion_seq OWNER TO postgres;

--
-- TOC entry 4988 (class 0 OID 0)
-- Dependencies: 231
-- Name: situacion_administrativa_id_situacion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.situacion_administrativa_id_situacion_seq OWNED BY public.situacion_administrativa.id_situacion;


--
-- TOC entry 224 (class 1259 OID 16429)
-- Name: vacaciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vacaciones (
    id_vacaciones integer NOT NULL,
    id_servidor integer,
    periodo_cubierto integer,
    fecha_inicio_disfrute date,
    fecha_fin_disfrute date,
    dias_disfrutados integer,
    saldo_pendiente_dias integer
);


ALTER TABLE public.vacaciones OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16428)
-- Name: vacaciones_id_vacaciones_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vacaciones_id_vacaciones_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vacaciones_id_vacaciones_seq OWNER TO postgres;

--
-- TOC entry 4989 (class 0 OID 0)
-- Dependencies: 223
-- Name: vacaciones_id_vacaciones_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vacaciones_id_vacaciones_seq OWNED BY public.vacaciones.id_vacaciones;


--
-- TOC entry 230 (class 1259 OID 16482)
-- Name: vinculacion_laboral; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vinculacion_laboral (
    id_vinculacion integer NOT NULL,
    id_servidor integer NOT NULL,
    id_cargo integer NOT NULL,
    fecha_ingreso date NOT NULL,
    fecha_retiro date,
    asignacion_mensual numeric(12,2),
    estado character varying(20),
    CONSTRAINT chk_estado_vinculacion CHECK (((estado)::text = ANY ((ARRAY['activo'::character varying, 'retirado'::character varying])::text[])))
);


ALTER TABLE public.vinculacion_laboral OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16481)
-- Name: vinculacion_laboral_id_vinculacion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vinculacion_laboral_id_vinculacion_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vinculacion_laboral_id_vinculacion_seq OWNER TO postgres;

--
-- TOC entry 4990 (class 0 OID 0)
-- Dependencies: 229
-- Name: vinculacion_laboral_id_vinculacion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vinculacion_laboral_id_vinculacion_seq OWNED BY public.vinculacion_laboral.id_vinculacion;


--
-- TOC entry 4778 (class 2604 OID 16400)
-- Name: cargo id_cargo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cargo ALTER COLUMN id_cargo SET DEFAULT nextval('public.cargo_id_cargo_seq'::regclass);


--
-- TOC entry 4777 (class 2604 OID 16393)
-- Name: dependencia id_dependencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dependencia ALTER COLUMN id_dependencia SET DEFAULT nextval('public.dependencia_id_dependencia_seq'::regclass);


--
-- TOC entry 4782 (class 2604 OID 16460)
-- Name: licencia id_licencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.licencia ALTER COLUMN id_licencia SET DEFAULT nextval('public.licencia_id_licencia_seq'::regclass);


--
-- TOC entry 4781 (class 2604 OID 16446)
-- Name: permiso id_permiso; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permiso ALTER COLUMN id_permiso SET DEFAULT nextval('public.permiso_id_permiso_seq'::regclass);


--
-- TOC entry 4779 (class 2604 OID 16412)
-- Name: servidor_publico id_servidor; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servidor_publico ALTER COLUMN id_servidor SET DEFAULT nextval('public.servidor_publico_id_servidor_seq'::regclass);


--
-- TOC entry 4784 (class 2604 OID 16518)
-- Name: situacion_administrativa id_situacion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.situacion_administrativa ALTER COLUMN id_situacion SET DEFAULT nextval('public.situacion_administrativa_id_situacion_seq'::regclass);


--
-- TOC entry 4780 (class 2604 OID 16432)
-- Name: vacaciones id_vacaciones; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vacaciones ALTER COLUMN id_vacaciones SET DEFAULT nextval('public.vacaciones_id_vacaciones_seq'::regclass);


--
-- TOC entry 4783 (class 2604 OID 16485)
-- Name: vinculacion_laboral id_vinculacion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vinculacion_laboral ALTER COLUMN id_vinculacion SET DEFAULT nextval('public.vinculacion_laboral_id_vinculacion_seq'::regclass);


--
-- TOC entry 4965 (class 0 OID 16397)
-- Dependencies: 220
-- Data for Name: cargo; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO public.cargo 
(id_cargo, denominacion, codigo, grado, id_dependencia)
VALUES
(1, 'Secretario de Despacho', 20, 10, 1),
(2, 'Director Administrativo', 9, 9, 2),
(3, 'Profesional Universitario', 219, 2, 3),
(5, 'Analista', 1001, 5, 1);
--
-- TOC entry 4963 (class 0 OID 16390)
-- Dependencies: 218
-- Data for Name: dependencia; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.dependencia
VALUES
(1, 'Secretaria de Planeacion', NULL),
(2, 'Secretaria de Hacienda', NULL),
(3, 'Secretaria de Educacion', NULL),
(4, 'Secretaria de Salud', 'Secretaria');


--
-- TOC entry 4973 (class 0 OID 16457)
-- Dependencies: 228
-- Data for Name: licencia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.licencia (id_licencia, id_servidor, tipo_licencia, fecha_inicio, fecha_fin) FROM stdin;


--
-- TOC entry 4971 (class 0 OID 16443)
-- Dependencies: 226
-- Data for Name: permiso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permiso (id_permiso, id_servidor, tipo_permiso, fecha, justificacion, num_dias) FROM stdin;



--
-- TOC entry 4967 (class 0 OID 16409)
-- Dependencies: 222
-- Data for Name: servidor_publico; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.servidor_publico
VALUES
(2, 79456123, 'M', 'Libre Nombramiento', 'andres.martinez@boyaca.gov.co', 1, 1, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 1067890123, 'M', 'Carrera Administrativa', 'diego.lopez@boyaca.gov.co', 2, 2, NULL, NULL, NULL, NULL, NULL, NULL),
(1, 1023456781, 'F', 'Carrera Administrativa', 'laura.gomez@boyaca.gov.co', 1, 1, NULL, NULL, NULL, NULL, NULL, NULL),
(5, 123456789, 'M', 'Planta', 'juan@boyaca.gov.co', 1, 1, 'Juan', 'Perez', NULL, NULL, NULL, NULL);


--
-- TOC entry 4977 (class 0 OID 16515)
-- Dependencies: 232
-- Data for Name: situacion_administrativa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.situacion_administrativa (id_situacion, id_servidor, tipo_situacion, fecha_inicio, fecha_fin, descripcion, id_acto_administrativo) FROM stdin;



--
-- TOC entry 4969 (class 0 OID 16429)
-- Dependencies: 224
-- Data for Name: vacaciones; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vacaciones (id_vacaciones, id_servidor, periodo_cubierto, fecha_inicio_disfrute, fecha_fin_disfrute, dias_disfrutados, saldo_pendiente_dias) FROM stdin;


--
-- TOC entry 4975 (class 0 OID 16482)
-- Dependencies: 230
-- Data for Name: vinculacion_laboral; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vinculacion_laboral (id_vinculacion, id_servidor, id_cargo, fecha_ingreso, fecha_retiro, asignacion_mensual, estado) FROM stdin;



--
-- TOC entry 4991 (class 0 OID 0)
-- Dependencies: 219
-- Name: cargo_id_cargo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cargo_id_cargo_seq', 5, true);


--
-- TOC entry 4992 (class 0 OID 0)
-- Dependencies: 217
-- Name: dependencia_id_dependencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dependencia_id_dependencia_seq', 6, true);


--
-- TOC entry 4993 (class 0 OID 0)
-- Dependencies: 227
-- Name: licencia_id_licencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.licencia_id_licencia_seq', 1, false);


--
-- TOC entry 4994 (class 0 OID 0)
-- Dependencies: 225
-- Name: permiso_id_permiso_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.permiso_id_permiso_seq', 1, false);


--
-- TOC entry 4995 (class 0 OID 0)
-- Dependencies: 221
-- Name: servidor_publico_id_servidor_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.servidor_publico_id_servidor_seq', 5, true);


--
-- TOC entry 4996 (class 0 OID 0)
-- Dependencies: 231
-- Name: situacion_administrativa_id_situacion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.situacion_administrativa_id_situacion_seq', 1, false);


--
-- TOC entry 4997 (class 0 OID 0)
-- Dependencies: 223
-- Name: vacaciones_id_vacaciones_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vacaciones_id_vacaciones_seq', 1, false);


--
-- TOC entry 4998 (class 0 OID 0)
-- Dependencies: 229
-- Name: vinculacion_laboral_id_vinculacion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vinculacion_laboral_id_vinculacion_seq', 1, false);


--
-- TOC entry 4791 (class 2606 OID 16402)
-- Name: cargo cargo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (id_cargo);


--
-- TOC entry 4789 (class 2606 OID 16395)
-- Name: dependencia dependencia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dependencia
    ADD CONSTRAINT dependencia_pkey PRIMARY KEY (id_dependencia);


--
-- TOC entry 4803 (class 2606 OID 16462)
-- Name: licencia licencia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.licencia
    ADD CONSTRAINT licencia_pkey PRIMARY KEY (id_licencia);


--
-- TOC entry 4801 (class 2606 OID 16450)
-- Name: permiso permiso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permiso
    ADD CONSTRAINT permiso_pkey PRIMARY KEY (id_permiso);


--
-- TOC entry 4793 (class 2606 OID 16417)
-- Name: servidor_publico servidor_publico_documento_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servidor_publico
    ADD CONSTRAINT servidor_publico_documento_key UNIQUE (cedula);


--
-- TOC entry 4795 (class 2606 OID 16415)
-- Name: servidor_publico servidor_publico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servidor_publico
    ADD CONSTRAINT servidor_publico_pkey PRIMARY KEY (id_servidor);


--
-- TOC entry 4807 (class 2606 OID 16523)
-- Name: situacion_administrativa situacion_administrativa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.situacion_administrativa
    ADD CONSTRAINT situacion_administrativa_pkey PRIMARY KEY (id_situacion);


--
-- TOC entry 4797 (class 2606 OID 16480)
-- Name: servidor_publico unique_cedula; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servidor_publico
    ADD CONSTRAINT unique_cedula UNIQUE (cedula);


--
-- TOC entry 4799 (class 2606 OID 16436)
-- Name: vacaciones vacaciones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vacaciones
    ADD CONSTRAINT vacaciones_pkey PRIMARY KEY (id_vacaciones);


--
-- TOC entry 4805 (class 2606 OID 16488)
-- Name: vinculacion_laboral vinculacion_laboral_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vinculacion_laboral
    ADD CONSTRAINT vinculacion_laboral_pkey PRIMARY KEY (id_vinculacion);


--
-- TOC entry 4808 (class 2606 OID 16403)
-- Name: cargo cargo_id_dependencia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cargo
    ADD CONSTRAINT cargo_id_dependencia_fkey FOREIGN KEY (id_dependencia) REFERENCES public.dependencia(id_dependencia);


--
-- TOC entry 4816 (class 2606 OID 16524)
-- Name: situacion_administrativa fk_situacion_servidor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.situacion_administrativa
    ADD CONSTRAINT fk_situacion_servidor FOREIGN KEY (id_servidor) REFERENCES public.servidor_publico(id_servidor);


--
-- TOC entry 4814 (class 2606 OID 16494)
-- Name: vinculacion_laboral fk_vinculacion_cargo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vinculacion_laboral
    ADD CONSTRAINT fk_vinculacion_cargo FOREIGN KEY (id_cargo) REFERENCES public.cargo(id_cargo);


--
-- TOC entry 4815 (class 2606 OID 16489)
-- Name: vinculacion_laboral fk_vinculacion_servidor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vinculacion_laboral
    ADD CONSTRAINT fk_vinculacion_servidor FOREIGN KEY (id_servidor) REFERENCES public.servidor_publico(id_servidor);


--
-- TOC entry 4813 (class 2606 OID 16463)
-- Name: licencia licencia_id_servidor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.licencia
    ADD CONSTRAINT licencia_id_servidor_fkey FOREIGN KEY (id_servidor) REFERENCES public.servidor_publico(id_servidor);


--
-- TOC entry 4812 (class 2606 OID 16451)
-- Name: permiso permiso_id_servidor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permiso
    ADD CONSTRAINT permiso_id_servidor_fkey FOREIGN KEY (id_servidor) REFERENCES public.servidor_publico(id_servidor);


--
-- TOC entry 4809 (class 2606 OID 16418)
-- Name: servidor_publico servidor_publico_id_cargo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servidor_publico
    ADD CONSTRAINT servidor_publico_id_cargo_fkey FOREIGN KEY (id_cargo) REFERENCES public.cargo(id_cargo);


--
-- TOC entry 4810 (class 2606 OID 16423)
-- Name: servidor_publico servidor_publico_id_dependencia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servidor_publico
    ADD CONSTRAINT servidor_publico_id_dependencia_fkey FOREIGN KEY (id_dependencia) REFERENCES public.dependencia(id_dependencia);


--
-- TOC entry 4811 (class 2606 OID 16437)
-- Name: vacaciones vacaciones_id_servidor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vacaciones
    ADD CONSTRAINT vacaciones_id_servidor_fkey FOREIGN KEY (id_servidor) REFERENCES public.servidor_publico(id_servidor);


-- Completed on 2026-05-25 13:33:04

--
-- PostgreSQL database dump complete
--
