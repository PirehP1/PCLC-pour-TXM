package org.txm.macro.stats

import org.txm.rcp.swt.widget.parameters.*
import org.txm.lexicaltable.core.functions.LexicalTable



// Sélection de la table lexicale

if (!(corpusViewSelection instanceof LexicalTable)) {
	println "Sélectionnez d'abord une table lexicale."
	return
}
LexicalTable lt = corpusViewSelection


// Définition du fichier de sortie

@Field @Option(name="Dossier", usage="Dossier", widget="Folder", required=true, def=".")
def Dossier

@Field @Option(name="Fichier", usage="Nom du fichier", widget="String", required=true, def="PCLC.tsv")
def Fichier

if (!ParametersDialog.open(this)) return

def cheminFichierSortie = Dossier.toString()+"/"+Fichier
def output = new File(cheminFichierSortie)
def writer = output.newWriter("UTF-8")




// Calcul des PCLC (longueur, nombre d'unités distinctes, nombre de hapax)

def nom = lt.getName()
def nCol = lt.getNColumns()
def nLig = lt.getNRows()
def nomParties = lt.getColNames().asStringsArray()
def mots = lt.getRowNames().asStringsArray()

def listeOccurences = new int[nCol]
def listeFormes =  new int[nCol]
def listeHapax = new int[nCol]
def listeFreqMax = new int[nCol]
def listeMotsFreqMax = new String[nCol]
	
for (int i = 0 ; i < nLig ; i++){
	ligne = lt.getRow(i).asIntArray()
	for (int j = 0 ; j < nCol ; j++){
		freq = ligne[j]
		listeOccurences[j] = listeOccurences[j] + freq
		if (freq > 0){
			listeFormes[j] = listeFormes[j]+1
		}
		if (freq == 1){
			listeHapax[j] = listeHapax[j] + 1
		}
		if (freq > listeFreqMax[j]){
			listeFreqMax[j] = freq
			listeMotsFreqMax[j] = mots[i]
		}
	}
}


// Préparation du tableau de résultats

def enTetes = "Partie\tLongueur\tNb. unités\tHapax\tFreq. max\tUnité"
def sortie = ""
sortie = sortie + enTetes + "\n"

for (int i = 0 ; i < nCol ; i++){
	def ligne = ""
	ligne = nomParties[i] + "\t" + listeOccurences[i] + "\t" + listeFormes[i] + "\t" + listeHapax[i] + "\t" + listeFreqMax[i] + "\t" + mots[i] + "\n"
	sortie += ligne
}


// Affichage et écriture du fichier de sortie

println sortie

writer.print sortie
writer.close()
println "Résultats enregistrés dans "+ cheminFichierSortie
