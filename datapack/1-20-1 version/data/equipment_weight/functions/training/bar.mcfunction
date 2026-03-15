execute if score @s training_ratio matches 1..9 run title @s actionbar [{"text":"⚒ Training: ░░░░░░░░░░ ","color":"gray"}, {"score":{"name":"@s","objective":"training_ratio"}}, {"text":"%"}]
execute if score @s training_ratio matches 10..19 run title @s actionbar [{"text":"⚒ Training: █░░░░░░░░░ ","color":"gray"}, {"score":{"name":"@s","objective":"training_ratio"}}, {"text":"%"}]
execute if score @s training_ratio matches 20..29 run title @s actionbar [{"text":"⚒ Training: ██░░░░░░░░ ","color":"gray"}, {"score":{"name":"@s","objective":"training_ratio"}}, {"text":"%"}]
execute if score @s training_ratio matches 30..39 run title @s actionbar [{"text":"⚒ Training: ███░░░░░░░ ","color":"gray"}, {"score":{"name":"@s","objective":"training_ratio"}}, {"text":"%"}]
execute if score @s training_ratio matches 40..49 run title @s actionbar [{"text":"⚒ Training: ████░░░░░░ ","color":"gray"}, {"score":{"name":"@s","objective":"training_ratio"}}, {"text":"%"}]
execute if score @s training_ratio matches 50..59 run title @s actionbar [{"text":"⚒ Training: █████░░░░░ ","color":"yellow"}, {"score":{"name":"@s","objective":"training_ratio"}}, {"text":"%"}]
execute if score @s training_ratio matches 60..69 run title @s actionbar [{"text":"⚒ Training: ██████░░░░ ","color":"yellow"}, {"score":{"name":"@s","objective":"training_ratio"}}, {"text":"%"}]
execute if score @s training_ratio matches 70..79 run title @s actionbar [{"text":"⚒ Training: ███████░░░ ","color":"yellow"}, {"score":{"name":"@s","objective":"training_ratio"}}, {"text":"%"}]
execute if score @s training_ratio matches 80..89 run title @s actionbar [{"text":"⚒ Training: ████████░░ ","color":"gold"}, {"score":{"name":"@s","objective":"training_ratio"}}, {"text":"%"}]
execute if score @s training_ratio matches 90..99 run title @s actionbar [{"text":"⚒ Training: █████████░ ","color":"gold"}, {"score":{"name":"@s","objective":"training_ratio"}}, {"text":"%"}]
execute if score @s training_ratio matches 100.. run title @s actionbar {"text":"⚒ Training: ██████████ 100%","color":"green"}
▓▒░