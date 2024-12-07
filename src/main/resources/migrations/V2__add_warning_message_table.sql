CREATE TABLE IF NOT EXISTS "warning_messages"
(
    "id" serial NOT NULL,
    "message" text NOT NULL,
    CONSTRAINT "warning_message_pkey" PRIMARY KEY ("id")
)