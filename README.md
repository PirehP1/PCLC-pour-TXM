# PCLC-pour-TXM
 Script Groovy pour calculer dans TXM les principales caractéristiques lexicométriques d'une partition, à la manière de Lexico. Il indique pour une partition donnée la longueur, le nombre de formes et de hapax de chaque partie.

## Installation
Mettre le fichier PCLC.groovy dans le dossier TXM > scripts > groovy > user

## Utilisation
1. À partir d'un corpus et d'une de ses partitions, créer dans TXM une table lexicale. Attention, il faut changer les paramètres `fmin` et `vmax` pour que la table lexicale recense bien l'ensemble des formes (ou autres unités) du corpus.
2. Sélectionner cette table lexicale.
3. Passer dans la vue Fichier de TXM, retrouver le script PCLC et l'executer.
4. Le tableau des principales caractéristiques lexicométriques est enregistré dans un fichier tsv (colonnes séparées par des tabulations) et il apparait dans la console.
