-- Migration to add completed column to tasks table
ALTER TABLE tasks ADD COLUMN completed BOOLEAN DEFAULT FALSE;
