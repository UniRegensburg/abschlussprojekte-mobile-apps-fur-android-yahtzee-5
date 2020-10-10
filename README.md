# Kniffel

Die App soll es ermöglichen am Handy Kniffel zu spielen. Die App ermöglicht es jederzeit und überall zu kniffeln, da Würfel, Würfelbecher und Block in der App integriert sind. Da sich Kniffel nicht an eine bestimmte Zielgruppe richtet, kann auch jeder mit einem Handy (Android-Betriebssystem) diese App herunterladen und Kniffel spielen. Der Vorteil des
digitalen Spiels ist es, dass man das Spiel praktisch immer bei sich hat und spontan mit seinen Freunden spielen kann. Das Ziel ist, dass die Spieler eine gute Zeit haben und das digitale Kniffeln genauso viel Spaß macht wie das Analoge.

#### Start-Activity
![MainActivity](https://user-images.githubusercontent.com/69957145/95654465-6d80df80-0b00-11eb-83c4-865ce869da7c.png)

Hier kann ausgewählt werden mit wie vielen Spielern das Spiel gestartet werden soll. Das Burger Menu ist hier wie in allen einigen anderen Activities aufrufbar. Über die Buttons der Spieleranzahl gelangt man zur InsertPlayersNamesActivity.

#### Burgermenü
![Burgermenü](https://user-images.githubusercontent.com/69957145/95654360-cb60f780-0aff-11eb-9177-f564133e329f.png)

Das Burgermenü ermöglicht die Navigation in verschieden Teilbereiche der App und ist in einigen Activities zu finden, in denen eine Navigtion in andere Bereiche sinnvoll ist.  

#### InsertName-Activity
![InsertName](https://user-images.githubusercontent.com/69957145/95654404-0ebb6600-0b00-11eb-8ffe-fc987a2929b2.png)

Hier können die Namen der Spieler eingetragen werden. Nach der Eingabe werden alle Namen zur Dice-Activity weitergeleitet.

#### RollTheDice-Activity
![DiceActivitySelected](https://user-images.githubusercontent.com/69957145/95654429-34486f80-0b00-11eb-9621-e50e3a991f33.png

Zeigt an, welcher Spieler an der Reihe ist und wie oft dieser noch würfeln darf. Es werden fünf Würfel angzeigt, die ausgewählt werden können, je nachdem welche behalten oder nochmal gewürfelt werden sollen. Mit einem Button können alle beispielsweise falsch ausgewählte Würfel in den "Becher"z  zurückgelegt werden. Gewürfelt wird indem man das Gerät schüttelt, wodurch zudem ein Vibrationseffekt ausgelöst wird. Der Button "Eintragen" ruft die TableActivity auf, in der Ergebnisse angeschaut und eingetragen werden können.

#### Table-Activity
![TableResults](https://user-images.githubusercontent.com/69957145/95654384-ef243d80-0aff-11eb-9fd9-76b75a42e2f6.png)

Hier kann der Spieler sich aussuchen in welches Feld er seine Würfelaugen eintragen will. Felder, in denen schon ein Eintrag steht, sind nicht mehr anwählbar genauso wie Felder der Kontrahenten. In dem angeklickten Feld wird im Optimalfall direkt das Ergebnis angezeigt oder ein Strich falls die gewürfelten Augen einen Eintrag nach den Regeln nicht zulassen. Mit dem Würfel-Button kommt man in die Dice Activity zurück und kann, falls man noch einen Wurf zur Verfügung hat, nochmal würfeln. Danach kann der Eintrag über den "Eintragen"-Button bestätigt werden. Daraufhin kehrt man in die Dice Activity zurück und der nächste Spieler ist an der Reihe. Wenn der letzte Spieler sein letztes Feld gefüllt hat, wird die GameOver-Activity aufgerufen, die die Endergebnisse auflistet.

#### GameOver-Activity
Hier wird das aus der Table Activity errechnete Endergebnis für jeden Spieler angezeigt. Die Activity enthält zwei Buttons "nochmal spielen" und "Highscores", welche den Spielern zu den entsprechenden Activities leiten.

#### Highscore-Activity
Eine simple Liste, die Name, Datum und Score der 10 besten Spieler Ergebnisse anzeigt.

#### Tutorial-Activity
![Tutorial](https://user-images.githubusercontent.com/69957145/95654509-ae78f400-0b00-11eb-9c5a-635e2b5238dd.png)
ViewPager mit LayoutScreens die je einen Screenshot, einen Titel und eine Beschreibung enthalten. Durch wischen kann zu einem anderen LayoutScreen gelangt werden. Am Ende des Tutorials kan das Spiel gestartet werden. 

#### PlayingRules-Activity
![Rules](https://user-images.githubusercontent.com/69957145/95654448-50e4a780-0b00-11eb-93c6-eb6a3e98cf8a.png)

Scrollbare Auflistung der Spielregeln


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


## Guidelines zur Nutzung dieses Repositorys

### Allgemeine Hinweise und Vorgaben

* Das Repository besteht im initialen Stand aus einem einzelnen Master-Branch. Versuchen Sie bei der Arbeit am Projekt darauf zu achten, dass sich in diesem Branch stets die aktuell lauffähige und fehlerfreie Version Ihrer Anwendung befindet. Nutzten Sie für die eigentliche Entwicklung ggf. weitere Branches.
* Gehen Sie sorgfältig bei der Erstellung von Issues und *Commit Messages* vor: Die Qualität dieser Artefakte fließt nicht in die Bewertung ein, trotzdem sollten Sie versuchen, Ihr Vorgehen anhand nachvollziehbarer Versionseinträge und klarere Aufgabenbeschreibung gut zu dokumentieren.
* Halten Sie diese Readme-Datei(en) stets aktuell.
* Spätestens zur Projektabgabe legen Sie eine Release-Version des finalen Stands Ihrer Anwendung an und hängen an diese eine installierbare (Debug-) APK-Datei an.
