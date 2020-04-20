INSERT INTO users (id, email, is_enabled, password) VALUES
(1, 'joe@mail.com', true,  '$2y$12$s/MA0.u8YD90qHTUP556x.lvBwbJl8V2v9yMH7ZZNTetz0ASWcicu'),
(2, 'bob@mail.com', false, '$2y$12$b5e9keCX8DwmLJKMKY8ov.wSKFkELzX9z1DfBSg1Epr3dT2jqVAO.'),
(3, 'tim@mail.com', true,  '$2y$12$WP4a9S5W7mvtWhsiRmJdC.kkSRFVmw9jxV07ZRlQTaOJOO596kyKC');

INSERT INTO categories (id, name) VALUES
(1, 'Administracja'),
(2, 'Bankowość'),
(3, 'IT - Rozwój oprogramowania'),
(4, 'Marketing');

INSERT INTO advertisements (id, title, content, date, user_id, category_id) VALUES
(1, 'Kierownik Projektów', 'Poszukujemy osoby z 3 letnim doświadczeniem na stanowisko kierownika projektu w branży IT', '2020-04-19 15:43:27.732454', 1, 1),
(2, 'Młodszy Kierownik Projektów', 'Poszukujemy osoby z 0,5 letnim doświadczeniem na stanowisko kierownika projektu w branży gastronomicznej', '2020-05-19 10:12:54.732454', 1, 1),
(3, 'Starszy Specjalista - Finanse', 'Poszukujemy osoby z 10 letnim doświadczeniem na stanowisko starszy specjalista do spraw finansów', '2020-01-19 12:12:12.122222', 1, 2),
(4, 'Programista aplikacji mobilnych', 'Poszukujemy osoby z 2 letnim doświadczeniem na stanowisko programisty mobilnych aplikacji w technologii Flutter', '2020-05-02 09:23:59.732454', 1, 3),
(5, 'Specjalista ds. sprzedaży internetowej', 'Poszukujemy osoby z 3 letnim doświadczeniem na stanowisko specjalista ds. sprzedaży internetowej', '2020-04-10 10:12:54.732454', 1, 4);

INSERT INTO job_applications (id, email, message, advertisement_id) VALUES
(1, 'joe@mail.com', 'Posiadam 5 lat doświadczenia jako programista apikacji mobilnych w technologii Flutter, czy możemy się skontakować?', 4),
(2, 'tim@mail.com', 'Posiadam 2 lata doświadczenia jako programista apikacji mobilnych. Proszę o kontakt, pozdrawiam!', 4),
(3, 'tim@mail.com', 'Posiadam rok doświadczenia w branży sprzedaży internetowej, lecz szybko się uczę. Proszę o kontakt :)', 5),
(4, 'joe@mail.com', 'Posiadam 4 lat doświadczenia jako analityk dużego europejskiego projektu IT, proszę o kontakt', 1);
