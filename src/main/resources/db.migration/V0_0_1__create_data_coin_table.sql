DROP TABLE IF EXISTS public.data_coin;

CREATE TABLE public.data_coin (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    code VARCHAR(10) NOT NULL,
    nama VARCHAR(15) NOT NULL,
    harga INT DEFAULT 0,
    updated_at TIMESTAMP
  );
