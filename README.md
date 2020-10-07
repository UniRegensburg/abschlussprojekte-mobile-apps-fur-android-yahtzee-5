# Kniffel

Die App soll es ermöglichen am Handy Kniffel zu spielen. Die App ermöglicht es jederzeit und
überall zu kniffeln, da Würfel, Würfelbecher und Block in der App integriert sind. Da sich
Kniffel nicht an eine bestimmte Zielgruppe richtet, kann auch jeder mit einem Handy
(Android-Betriebssystem) diese App herunterladen und Kniffel spielen. Der Vorteil des
digitalen Spiels ist es, dass man das Spiel praktisch immer bei sich hat und spontan mit
seinen Freunden spielen kann. Das Ziel ist, dass die Spieler eine gute Zeit haben und das
digitale Kniffeln genauso viel Spaß macht wie das Analoge.

#### Start-Activity
![StartActivity](C:\Users\LenaG\OneDrive\Desktop\abschlussprojekte-mobile-apps-fur-android-yahtzee-5\app\src\main\res\drawable-mdpi\tutorial_main.png)
Ist zum Start der App zu sehen, und nach der Game Over Activity. Hier kann
ausgewählt werden mit wie vielen Spielern das Spiel gestartet werden soll. Das Burger Menu
ist hier wie in allen anderen Activities aufrufbar. Über die Buttons der Spieleranzahl gelangt
man zur Insert PlayersNames Activity.

#### InsertName-Activity
Zum Eintragen der Spielernamen. Leitet nach der Eingabe alle Namen zur Dice-Activity.

#### RollTheDice-Activity
Zeigt an, welcher Spieler an der Reihe ist und wie oft dieser noch würfeln darf.Zeigt vor dem ersten Schütteln keine Würfel an, danach fünf Würfel, die ausgewählt werden können, je nachdem welche behalten werden sollen oder nochmal gewürfelt werden. Hat einen Buttons. Der Scoreboard-Button führt zur Table Activity.

#### Table-Activity
Hier kann der Spieler sich aussuchen in welches Feld er seine Augen eintragen will. Das angeklickte Feld wird grün hinterlegt. Felder, in denen schon ein Eintrag steht, sind nicht mehr anwählbar genauso wie Felder der Kontrahenten. In dem angeklickten Feld wird im Optimalfall direkt das Ergebnis angezeigt oder ein Strich falls die gewürfelten Augen einen Eintrag nach den Regeln nicht zulassen. Mit dem Würfel-Button kommt man in die Dice Activity zurück und kann, falls man noch einen Wurf zur Verfügung hat, nochmal würfeln. Danach kann der Eintrag über den eintragen Button bestätigt werden. Daraufhin kehrt man in die Dice Activity zurück und der nächste Spieler ist dran. Wenn der letzte Spieler sein letztes Feld gefüllt hat man in die GameOver-Activity.

#### GameOver-Activity
Hier wurde das Ergebnis der Table Activity ausgerechnet und angezeigt wer gewonnen hat. Mit dem Button „Scoreboard“ lässt sich das finale Scoreboard nochmal anzeigen und mit dem Button „Main Menu“ kehrt man in die Start-Activity zurück. Highscore-Activity: Eine simple Liste, die Name, Datum und Score der 10 besten Spieler Ergebnisse anzeigt.

#### Tutorial-Activity
ViewPager mit LayoutScreens die je einen Screenshot, einen Titel und eine Beschreibung enthalten. Durch wischen kann zu einem anderen LayoutScreen gelangt werden. Am Ende des Tutorials kan das Spiel gestartet werden. 

#### PlayingRules-Activity
Scrollbare Auflistung der Spielregeln

[Beschreiben Sie hier in einer kurzen Zusammenfassung Hintergrund, Ziele und Funktionen Ihrer Anwendung. Fügen Sie mehrere sinnvollen Screenshot ein, die den Funktionsumfang der Anwendungdemonstrieren. Verlinken Sie zum Abschluss des Projekts ein kurzes Video, in dem Sie die wesentlichen Nutzungsszenarien Ihrer Anwendung demonstrieren.]

## Team

Franka Heinlein – Franka-Heinlein@stud.uni-regensburg.de
- Datenbank 
- Burger-Menü
- Highscore-Liste mit eigenem Adapter
- responsives Layout mittels ContraintLayout

Lena Germiller – Lena-Germiller@stud.uni-regensburg.de
- Auslesen und Verwenden von Accelerometer + Vibration
- Shared Preferences bei Tutorial
- horizontales RecyclerView + eigenen Adapter für RecyclerView + Spieler-Klasse
- responsives Layout mittels ContraintLayout

Quentin Vijverberg – quentin-vijverberg@gmx.de
- horizontales RecyclerView + eigenen Adapter für RecyclerView 
- responsives Layout mittels ContraintLayout
- Spiellogik 
- Anzeigen + Eintragen der Ergebnisse in TableActivity

[Beschreiben Sie hier die einzelnen Teammitglieder mit Namen, E-Mail-Adresse, Github-Nutzer und Foto. Nennen Sie mindestens eine Komponenten der Anwendung, die in wesentlichen Teilen vom jeweiligen Teammitglied entwickelt wurde.]

## Guidelines zur Nutzung dieses Repositorys

### Allgemeine Hinweise und Vorgaben

* Das Repository besteht im initialen Stand aus einem einzelnen Master-Branch. Versuchen Sie bei der Arbeit am Projekt darauf zu achten, dass sich in diesem Branch stets die aktuell lauffähige und fehlerfreie Version Ihrer Anwendung befindet. Nutzten Sie für die eigentliche Entwicklung ggf. weitere Branches.
* Gehen Sie sorgfältig bei der Erstellung von Issues und *Commit Messages* vor: Die Qualität dieser Artefakte fließt nicht in die Bewertung ein, trotzdem sollten Sie versuchen, Ihr Vorgehen anhand nachvollziehbarer Versionseinträge und klarere Aufgabenbeschreibung gut zu dokumentieren.
* Halten Sie diese Readme-Datei(en) stets aktuell.
* Spätestens zur Projektabgabe legen Sie eine Release-Version des finalen Stands Ihrer Anwendung an und hängen an diese eine installierbare (Debug-) APK-Datei an.
