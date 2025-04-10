-- Active: 1727983525436@@127.0.0.1@5432@proyecto_repaso

CREATE TABLE rarities (
    rarity_id INT PRIMARY KEY,
    rarity_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE loot (
    loot_id INT PRIMARY KEY,
    loot_type VARCHAR(50) NOT NULL,
    loot_value INT NOT NULL,
    loot_rarity INT NOT NULL,
    FOREIGN KEY (loot_rarity) REFERENCES rarities(rarity_id)
);

CREATE TABLE enemies (
    enemy_id INT PRIMARY KEY,
    enemy_damage INT NOT NULL,
    enemy_name VARCHAR(100) NOT NULL,
    enemy_health INT NOT NULL
);

CREATE TABLE basic_enemies (
    enemy_id INT PRIMARY KEY,
    FOREIGN KEY (enemy_id) REFERENCES enemies(enemy_id)
);

CREATE TABLE elite_enemies (
    enemy_id INT PRIMARY KEY,
    enemy_armor INT UNIQUE NOT NULL,
    FOREIGN KEY (enemy_id) REFERENCES enemies(enemy_id)
);

CREATE TABLE cards (
    card_id INT PRIMARY KEY,
    card_rarity INT NOT NULL,
    mana_cost INT NOT NULL,
    FOREIGN KEY (card_rarity) REFERENCES rarities(rarity_id)
);

CREATE TABLE card_packs (
    pack_id INT PRIMARY KEY
);

CREATE TABLE players (
    player_id INT PRIMARY KEY,
    player_name VARCHAR(100) NOT NULL,
    player_score INT NOT NULL
);

CREATE TABLE tasks (
    task_id INT PRIMARY KEY,
    task_info TEXT NOT NULL,
    task_date DATE NOT NULL
);

CREATE TABLE completed_tasks (
    completed_by INT NOT NULL,
    task_id INT NOT NULL,
    completed_task_date DATE NOT NULL,
    completed_task_info TEXT NOT NULL,
    PRIMARY KEY (completed_by, task_id),
    FOREIGN KEY (completed_by) REFERENCES players(player_id),
    FOREIGN KEY (task_id) REFERENCES tasks(task_id)
);

--IF EXISTS IN ONE OR THE OTHER TABLE (BASIC,ELITE), I HAVE TO MAKE IT SO THAT SPECIFIC LOGIC OCCURS FOR EACH ONE
CREATE TABLE drop_loot (
    enemy_id INT NOT NULL,
    loot_id INT NOT NULL,
    PRIMARY KEY (enemy_id, loot_id),
    FOREIGN KEY (enemy_id) REFERENCES enemies(enemy_id),
    FOREIGN KEY (loot_id) REFERENCES loot(loot_id)
);

create table cards_in_packs (
    pack_id INT NOT NULL,
    card_id INT NOT NULL,
    PRIMARY KEY (pack_id, card_id),
    FOREIGN KEY (pack_id)
        REFERENCES card_packs(pack_id),
    FOREIGN KEY (card_id)
        REFERENCES cards(card_id)
);

create table unlocked_by (
    player_id INT NOT NULL,
    card_id INT NOT NULL,
    PRIMARY KEY (player_id, card_id),
    FOREIGN KEY (player_id)
        REFERENCES players(player_id),
    FOREIGN KEY (card_id)
        REFERENCES cards(card_id)
);

CREATE TABLE completing_task (
    player_id INT NOT NULL,
    task_id INT NOT NULL,
    PRIMARY KEY (player_id, task_id),
    FOREIGN KEY (player_id) REFERENCES players(player_id),
    FOREIGN KEY (task_id) REFERENCES tasks(task_id)
);