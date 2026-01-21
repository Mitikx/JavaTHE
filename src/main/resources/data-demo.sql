-- Script SQL pour insérer des données de test dans la boutique de thés
-- À exécuter après le démarrage de l'application (Hibernate créera les tables automatiquement)

USE boutique_thes;

-- Insertion de produits de démonstration
INSERT INTO produit (nom, type_the, origine, prix, quantite_stock, description, date_reception) VALUES
('Sencha Premium', 'Vert', 'Japon', 28.50, 75, 'Thé vert japonais de première récolte, goût frais et végétal avec notes marines', '2026-01-15'),
('Dragon Well (Longjing)', 'Vert', 'Chine', 45.00, 30, 'Thé vert chinois cultivé à Hangzhou, saveur douce et légèrement sucrée', '2026-01-10'),
('Darjeeling First Flush', 'Noir', 'Inde', 32.00, 50, 'Thé noir léger et délicat, récolte de printemps avec notes florales', '2026-01-12'),
('Ceylon Orange Pekoe', 'Noir', 'Sri Lanka', 22.00, 100, 'Thé noir corsé avec une belle couleur cuivrée, idéal pour le petit-déjeuner', '2026-01-18'),
('Tie Guan Yin', 'Oolong', 'Taiwan', 52.00, 25, 'Oolong premium légèrement torréfié, arômes floraux et notes de miel', '2026-01-08'),
('Da Hong Pao', 'Oolong', 'Chine', 68.00, 15, 'Oolong des montagnes Wuyi, saveur complexe avec notes minérales', '2025-12-20'),
('Bai Mu Dan (Pivoine Blanche)', 'Blanc', 'Chine', 38.00, 40, 'Thé blanc délicat composé de bourgeons et jeunes feuilles, saveur subtile', '2026-01-14'),
('Silver Needle', 'Blanc', 'Chine', 55.00, 20, 'Le plus raffiné des thés blancs, uniquement des bourgeons argentés', '2026-01-05'),
('Pu-erh Shou (Cuit)', 'Pu-erh', 'Chine', 42.00, 35, 'Pu-erh fermenté aux notes terreuses et boisées, facilite la digestion', '2025-11-30'),
('Gyokuro', 'Vert', 'Japon', 62.00, 18, 'Thé vert japonais d''ombre, saveur umami intense et douce', '2026-01-16'),
('Earl Grey Premium', 'Noir', 'Inde', 25.00, 80, 'Thé noir parfumé à la bergamote, mélange classique et raffiné', '2026-01-20'),
('Jasmine Phoenix Pearls', 'Vert', 'Chine', 35.00, 45, 'Thé vert roulé en perles, parfumé aux fleurs de jasmin fraîches', '2026-01-11'),
('Matcha Ceremonial', 'Vert', 'Japon', 48.00, 28, 'Poudre de thé vert de qualité cérémoniale, riche en antioxydants', '2026-01-19'),
('Assam TGFOP', 'Noir', 'Inde', 28.00, 65, 'Thé noir maltés et corsé, parfait avec du lait', '2026-01-13'),
('Oriental Beauty', 'Oolong', 'Taiwan', 58.00, 22, 'Oolong oxydé naturellement par des insectes, notes fruitées et miellées', '2026-01-09');

-- Vérification des insertions
SELECT COUNT(*) AS 'Nombre de produits' FROM produit;

-- Affichage de tous les produits
SELECT * FROM produit ORDER BY date_reception DESC;
