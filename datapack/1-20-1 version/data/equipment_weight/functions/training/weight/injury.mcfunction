scoreboard players remove @s training_progress 1
execute if score @s training_progress matches 1.. run tellraw @s {"text":"You are hurting your muscles, carefull!!","color":"red"}