CREATE  FUNCTION update_updated_at_tb_users()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.US_UPDATED_AT = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_users_task_updated_on
    BEFORE UPDATE
    ON
        TB_USERS
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_tb_users();

--

CREATE  FUNCTION update_updated_at_tb_roles()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.RL_UPDATED_AT = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_roles_task_updated_on
    BEFORE UPDATE
    ON
        TB_ROLES
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_tb_roles();

--

CREATE  FUNCTION update_updated_at_tb_users_roles()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.UR_UPDATED_AT = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_users_roles_task_updated_on
    BEFORE UPDATE
    ON
        TB_USERS_ROLES
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_tb_users_roles();

--

CREATE  FUNCTION update_updated_at_tb_movies()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.MO_UPDATED_AT = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_movies_task_updated_on
    BEFORE UPDATE
    ON
        TB_MOVIES
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_tb_movies();

--

CREATE  FUNCTION update_updated_at_tb_sessions()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.SS_UPDATED_AT = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_sessions_task_updated_on
    BEFORE UPDATE
    ON
        TB_SESSIONS
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_tb_sessions();

--

CREATE  FUNCTION update_updated_at_tb_seats()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.ST_UPDATED_AT = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_seats_task_updated_on
    BEFORE UPDATE
    ON
        TB_SEATS
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_tb_seats();

--

CREATE  FUNCTION update_updated_at_tb_rooms()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.RO_UPDATED_AT = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_rooms_task_updated_on
    BEFORE UPDATE
    ON
        TB_ROOMS
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_tb_rooms();

--

CREATE  FUNCTION update_updated_at_tb_rooms_sessions()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.RS_UPDATED_AT = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_rooms_sessions_task_updated_on
    BEFORE UPDATE
    ON
        TB_ROOMS_SESSIONS
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_tb_rooms_sessions();

--

CREATE  FUNCTION update_updated_at_tb_rooms_seats()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.RST_UPDATED_AT = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_rooms_seats_task_updated_on
    BEFORE UPDATE
    ON
        TB_ROOMS_SEATS
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_tb_rooms_seats();

--

CREATE  FUNCTION update_updated_at_tb_reservations()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.RES_UPDATED_AT = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_reservations_task_updated_on
    BEFORE UPDATE
    ON
        TB_RESERVATIONS
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_tb_reservations();

--

CREATE  FUNCTION update_updated_at_tb_reservations_room_seat()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.RRS_UPDATED_AT = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_reservations_room_seat_task_updated_on
    BEFORE UPDATE
    ON
        TB_RESERVATIONS_ROOM_SEAT
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_tb_reservations_room_seat();

--

CREATE  FUNCTION update_updated_at_tb_movie_tickets()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.MVT_UPDATED_AT = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_movie_tickets_task_updated_on
    BEFORE UPDATE
    ON
        TB_MOVIE_TICKETS
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_tb_movie_tickets();

--

CREATE  FUNCTION update_updated_at_tb_reservations_movies_tickets()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.RMT_UPDATED_AT = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_reservations_movies_tickets_task_updated_on
    BEFORE UPDATE
    ON
        TB_RESERVATIONS_MOVIES_TICKETS
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_tb_reservations_movies_tickets();

--

CREATE  FUNCTION update_updated_at_tb_genres()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.GR_UPDATED_AT = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_genres_task_updated_on
    BEFORE UPDATE
    ON
        TB_GENRES
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_tb_genres();

--

CREATE  FUNCTION update_updated_at_tb_movies_genres()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.MG_UPDATED_AT = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_movies_genres_task_updated_on
    BEFORE UPDATE
    ON
        TB_MOVIES_GENRES
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_tb_movies_genres();