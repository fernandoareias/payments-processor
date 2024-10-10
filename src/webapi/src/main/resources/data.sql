
CREATE TABLE public.db_credit_card_payments (
	id int8 NOT NULL,
	created_at timestamptz(6) NULL,
	aggregate_id uuid NULL,
	amount numeric(38, 2) NULL,
	cardcvc varchar(255) NULL,
	card_expiry varchar(255) NULL,
	card_number varchar(255) NULL,
	recipient_document varchar(255) NULL,
	status int2 NULL,
	payment_processor_id int8 NULL,
	CONSTRAINT db_credit_card_payments_pkey PRIMARY KEY (id),
	CONSTRAINT db_credit_card_payments_status_check CHECK (((status >= 0) AND (status <= 8))),
	CONSTRAINT fkhuuknr9y0fuqyor1wblokrun8 FOREIGN KEY (payment_processor_id) REFERENCES public.db_payment_processor(id)
);


CREATE TABLE public.db_payment_processor (
	id int8 NOT NULL,
	created_at timestamptz(6) NULL,
	"name" varchar(255) NULL,
	CONSTRAINT db_payment_processor_pkey PRIMARY KEY (id)
);


CREATE table if not EXISTS public.db_processor (
    id BIGSERIAL PRIMARY KEY,
    created_at timestamptz(6) NULL,
    external_id varchar(255) NULL,
    response varchar(255) NULL,
    status int2 NULL,
    processors int8 NULL,
    CONSTRAINT db_processor_status_check CHECK (((status >= 1) AND (status <= 2))),
    CONSTRAINT fk9ie12o6nkyncy07eisul4er92 FOREIGN KEY (processors) REFERENCES public.db_credit_card_payments(id)
);