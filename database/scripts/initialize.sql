CREATE SCHEMA IF NOT EXISTS PlanningPoker;

CREATE TABLE PlanningPoker.player (
    player_id UUID PRIMARY KEY,
    display_name VARCHAR(255),
    player_type VARCHAR(255),
    game_id UUID
);

CREATE TABLE PlanningPoker.game (
    game_id UUID PRIMARY KEY,
    display_name VARCHAR(255),
    start_time TIMESTAMP,
    owner_id UUID,
    current_card_id UUID
);

CREATE TABLE PlanningPoker.card (
    card_id UUID PRIMARY KEY,
    game_id UUID
);

CREATE TABLE PlanningPoker.vote (
    vote_id UUID PRIMARY KEY,
    card_id UUID,
    player_id UUID,
    estimate VARCHAR(255)
);

ALTER TABLE PlanningPoker.player
ADD FOREIGN KEY (game_id) REFERENCES PlanningPoker.game(game_id);

ALTER TABLE PlanningPoker.game
ADD FOREIGN KEY (owner_id) REFERENCES PlanningPoker.player(player_id),
ADD FOREIGN KEY (current_card_id) REFERENCES PlanningPoker.card(card_id);

ALTER TABLE PlanningPoker.card
ADD FOREIGN KEY (game_id) REFERENCES PlanningPoker.game(game_id);

ALTER TABLE PlanningPoker.vote
ADD FOREIGN KEY (card_id) REFERENCES PlanningPoker.card(card_id),
ADD FOREIGN KEY (player_id) REFERENCES PlanningPoker.player(player_id);