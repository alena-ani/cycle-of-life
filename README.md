# cycle-of-life
Симулятор острова заполняющийся растениями и животными.
Животные могут: есть растения или других животных,
передвигаться в соседние локации, умирать от голода или
быть съеденными.

Краткое описание классов:

В пакете provider находятся классы AttributeProvider,
EatingProbabilityProvider поставляющие характеристики
животных.

В пакете parameters находятся файлы характеристики
животных и вероятность поедания животных или растении.

В пакете util находится утильный класс RandomUtil
содержащий методы getCoordinate, getDirection,
getNumber генерирующие произвольно  направление 
движения, координаты локации и произвольный номер.

В пакете model находятся покеты:
animal находятся все животные, класс родитель Animal
содержащий общие поля и методы всех существ,
классы AnimalAttributes,
AnimalPopulation, AnimalType, AnimalWorld отвечающие за
тип животных, популяции животных и животный мир;

plant находятся классы отвечающие за растения,
популяцию растений и мир растений;

statistic находятся классы модели для статистической 
информации.

В покете factory находятся классы, создающие животных
и растения

В покете manager находятся классы:
IcelandManager отвечающие за основную логику управления
действиями острова, содержит методы заполнения
острова растениями и животными, жизнь острова,
демонстрации в консоль.
WalkManager - передвижение животных по локациям,
FeedManager - поедание животных и растений,
BirthManager - рождение новых животных,
CleanManager - удаление истощенных животных и
умерших животных,
StatisticManager - сбор статистики за каждый день.

Пример работы программы
Остров размерами 100*20.
При запуске выводиться количество животных
заселённых на острове
В каждой локации выводиться количество животных 
отобразивщихся и количество всех животных в лакации
Total number of animals: 119630
🐰 (  93/ 556)  🦊 (  13/1098)  🐻 (   1/ 735)   🐗 (  22/ 838)  🐃 (   4/1478)   🐐 ( 107/1167)   🦤 (  10/1500)    🐭 ( 166/ 727)  
🐛 ( 666/1086)  🐻 (   3/1923)  🐍 (   4/1239)   🐭 ( 453/1638)  🐗 (  22/1367)   🦌 (   3/ 702)   🐍 (   3/1441)    🐗 (  14/ 646
🐐 ( 118/1619)  🐭 ( 162/1269)  🦤 (   4/1714)   🐺 (   9/1375)  🐗 (   2/1107)   🐛 ( 941/1791)   🦤 (  19/ 576)    🐏 ( 108/ 902
🐏 ( 123/1601)  🦌 (  19/ 779)  🐰 (  68/1561)   🐎 (   7/1397)  🐭 ( 220/1394)   🐎 (   7/1510)   🦆 ( 193/1387)    🐗 (  33/ 867)    
🐰 ( 121/1305)  🐛 ( 785/1534)  🦌 (   6/1607)   🐻 (   1/1587)  🐐 (  84/ 863)   🐻 (   4/1348)   🐻 (   3/ 683)    🐐 (  55/1693)
🦊 (  13/1083)  🦆 (  62/1393)  🐗 (  23/1583)   🦆 (  40/ 808)  🐎 (   4/1196)   🐛 ( 275/ 956)   🐰 (  28/ 975)    🦆 (  99/ 479
🐃 (   3/ 551)  🦌 (  18/1671)  🦊 (   2/1557)   🦊 (  19/1355)  🐛 ( 166/ 756)   🐛 ( 891/1387)   🐛 ( 209/1069)    🐏 (  67/1323)    
🐍 (   3/1260)  🐺 (   3/1251)  🦊 (   9/ 773)   🦊 (  29/ 874)  🦊 (  17/1848)   🦌 (   4/1489)   🐺 (  24/ 922)    🐏 (  24/ 987)    
🐭 (  59/1352)  🐭 ( 316/1285)  🐛 ( 747/1490)   🦤 (   2/1682)  🦤 (   1/ 888)   🦤 (  18/1678)   🐐 ( 107/1067)    🐭 ( 280/1198)    
🐰 (  45/1193)  🦤 (  12/1187)  🐏 (  99/1162)   🐛 ( 509/1168)  🐰 ( 128/ 954)   🦌 (  12/ 557)   🦤 (  18/1078)    🐐 ( 111/1404)

Начинается следущий день
Выводиться статитстика по лакациям и остров с животными:
Animal type: MOUSE at Coordinate {row=2, column=1}
The number of animals that ate: 85
The number of animals that were born: 42
The number of animals that died: 49




