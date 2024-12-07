CREATE TABLE IF NOT EXISTS "member_activity"
(
    "discord_id" text NOT NULL,
    "member_name" text NOT NULL,
    "last_active" timestamp NOT NULL,
    "inactive" boolean NOT NULL,
    CONSTRAINT "member_activity_pkey" PRIMARY KEY ("discord_id")
);