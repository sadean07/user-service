DROP TABLE IF EXISTS public.user_has_coin;

CREATE TABLE public.user_has_coin (
    user_id BIGINT NOT NULL,
    coin_id BIGINT NOT NULL,
    jumlah INT DEFAULT 0,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.data_user(id),
    CONSTRAINT fk_coin_id FOREIGN KEY (coin_id) REFERENCES public.data_coin(id)
  );
