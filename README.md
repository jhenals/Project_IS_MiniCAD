# MiniCAD
MiniCAD è un'applicazione che consente la creazione, manipolazione e gestione di oggetti grafici bidimensionali. Questo progetto include un interprete di comandi che permette di interagire con l'applicazione tramite comandi testuali.

----------------------------------------------------------------------------
## TABLE OF CONTENTS
- [Servizi](#servizi)
- [MiniCAD GUI](#minicad-gui)
- [Requisiti](#requisiti)
- [Struttura del Codice](#struttura-del-codice)
- [Testing](#testing)

-------------------------------------------------------------------------------------
## Servizi
1. Creazione 
2. Rimozione 
3. Spostamento 
4. Ridimensionamento
5. Visualizzazione delle proprietà
6. Creazione di gruppi di oggetti
7. Rimozione di gruppi di oggetti
8. Calcolo di area e perimetro

## MiniCAD GUI
L'interfaccia utente permette agli utenti di interagire con il sistema MiniCAD tramite pulsanti e una visualizzazione grafica degli oggetti.
![img.png](img.png)
### Utilizzo MiniCAD GUI
1. Avviare la classe _MiniCAD/ui/MiniCADUI.java_.
2. All'avvio dell'applicazione, verrà visualizzataa una finestra con una toolbar, un'area di disegno e il panello per i comandi.
3. Utilizza i pulsanti sulla toolbar per crrare nuovi oggetti grafici.
4. Clicca sugli oggetti nell'area di disegno per selezionarli e poter utilizzare i pulsanti di comandi.
5. Per i group object, bisogna inserire l'id del gruppo nel textfield per i gruppi e clicca il pulsante GO.
6. Usa il pulsante "Undo" sulla toolbar per annullare l'ultima operazione.


## Requisiti
- JAVA21
- Librerie esterne : nessuna


## Struttura del Codice
* **MiniCAD/ui/MiniCADUI.java** - contiene la classe principale MiniCADUI che avvia l'applicazione e gestisce l'interfaccia utente.
* **MiniCAD/shapes/interpreter/commands** - contiene i comandi implementati con Interpreter Pattern.
* **MiniCAD/shapes/interpreter/lexerparser** - contiene le classi Lexer e Parser 
* **MiniCAD/shapes/interpreter/GroupObject.java** - classe per gestire i gruppi
* **MiniCAD/shapes/interpreter/Context.java** - classe context
* **MiniCAD/shapes/controllers/MiniCADController.java** - controller che gestiscono l'interazione tra l'interfaccia utente e il modello.
* **MiniCAD/shapes/view/CreateObjectActionMiniCad.java** - utilizzata per creare e gestire l'azione di creazione di oggetti grafici nell'applicazione MiniCAD

## Testing
Il progetto utilizza JUnit per il testing. 