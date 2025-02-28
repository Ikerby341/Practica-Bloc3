# Practica-Bloc3
# Enunciat

La multinacional SAPAMERCAT us demana que li dissenyeu una aplicació revolucionaria! Consisteix en fer que el carro de la compra mostri, en temps real, el preu dels productes que s'hi van introduint.

L'empresa us indica que l'aplicació, de moment, només ha de permetre gestionar les dades d'uns quants dels seus productes: alimentació, tèxtil i electrònica. Aquests productes tenen unes característiques comuns (preu, nom i codi de barres) i un conjunt de característiques específiques de cada tipus de producte:

Alimentació: data de caducitat.

El preu d'aquest tipus de producte varia en funció dels dies que falten per caducar, segons la fórmula:

             preu - preu*(1/(dataCaducitat-dataActual+1)) + (preu * 0.1)

Tèxtil: composició tèxtil (text)

Electrònica: dies de garantia (numèric)

El preu d'aquest tipus de producte varia en funció dels dies que té de garantia segons la fórmula:

         preu + preu*(diesGarantia/365)*0.1

L'aplicació que heu de fer ha de permetre gestionar tots els productes que s'hi van introduint (màxim 100 productes) i calcular-ne el preu. 

També ha de permetre que, en passar per caixa, es generi el tiquet de compra i es buidi el carro.

Fer un programa principal que faci ús de les classes dissenyades. La descripció del que ha de fer aquest programa s'explica a continuació. El programa ha de tenir un menú d'opcions com el següent:

1. Gestió Magatzem i Compres Gestió dels productes que trobem al magatzem i gestió de l'historial de tiquets de compra.

1.1. Caducitat En escollir aquesta opció es llistarà els productes en funció de la seva data de caducitat (els més pròxims a caducar tindran més prioritat per ser processats primer en passar per caixa).

1.2. Tiquets de compra En escollir aquesta opció guardarem un registre de les compres realitzades per poder-hi accedir més tard. Serà una estructura on afegirem nous tiquets de manera seqüencial.

1.3. Composició tèxtil En escollir aquesta opció gestionarem els productes tèxtils per a ordenar-los per la seva composició tèxtil.  No podrem tenir dos productes amb el mateix codi de barres. Aquesta ordenació la farem servir també pels tèxtils del carret de la compra.

1.0. Tornar En escollir aquesta opció s'ha de tornar al menú principal

2. Introduir producte En escollir aquesta opció s'ha de mostrar un altre menú d'opcions: Quin tipus de producte vols afegir?

2.1. Alimentació En escollir aquesta opció s'ha de demanar que s'entri per teclat les dades d'un producte del tipus Alimentació

2.2. Tèxtil En escollir aquesta opció s'ha de demanar que s'entri per teclat les dades d'un producte del tipus Tèxtil

2.3. Electrònica En escollir aquesta opció s'ha de demanar que s'entri per teclat les dades d'un producte del tipus Electrònica

2.0. Tornar En escollir aquesta opció s'ha de tornar al menú principal

3. Passar per caixa En escollir aquesta opció se simula que es passen tots els productes per caixa i es genera el tiquet.

El tiquet (es mostra per pantalla) ha de mostrar una capçalera amb: data de la compra i nom del supermercat. A continuació es mostra el detall amb: nom del producte, unitats introduïdes al carro, preu unitari i preu total. Finalment ha de calcular la suma total a pagar.

Si s'han introduït dos productes iguals (tenen el mateix codi de barres i el mateix preu unitari) només es mostrarà una vegada, amb la quantitat total d'aquell producte, és a dir, les unitats.

Aquesta opció també implica buidar el carro de la compra.

4. Mostrar carro de la compra En escollir aquesta opció es mostra un llistat amb la descripció i quantitat de cada producte (sense preu) que hi ha dins el carro del a compra. En aquest cas, si hi ha productes repetits ho seran si tenen el mateix codi de barres (no cal mirar el preu unitari).

0. Sortir En escollir aquesta opció es tanca l'aplicació.

Des del cap de departament de informàtica, el cap de projecte ens demana els següents requeriments tècnics per a que el projecte quedi el màxim d’integrat possible amb altres projectes ja desplegats al client.
·         Cal realitzar el projecte en entorn Git, realitzant els commits necessaris que facilitin saber l’evolució del vostre projecte.

·         Configureu el .gitignore degudament

·         Cal dissenyar un README.md que deixi clar les vostres justificacions i decisions que heu pres.

·         Cal declarar en tot moment els getters i setters de cada classe

·         Cal documentar tot el codi degudament

·         Ens demanen treballar amb la Collection List, sabem que tant Stack com a Vector funcionen correctament per a processos multithreading però en principi no ens cal dins del nostre context, per tant valoreu, escolliu i justifiqueu quin dels altres dos casos faríeu servir i a on?

·         Per a poder-lo integrar amb la impressió del carret de la compra d’altres aplicacions ja desplegades, ens demanen treballar amb la Collection Map, i ens diuen que serà necessari treballar amb mètodes propis com ara containsKey o containsValue (valoreu quin dels dos casos us serà necessari). El recorregut de les dades s’haurà de fer amb lambda expressions.

·         Cal implementar la interfície Comparable amb el seu corresponent mètode en una classe que considereu que només cal fer una ordenació natural, i per tant, ens cal també implementar en una altra classe la interfície Comparator amb el seu mètode corresponent definit per vosaltres i que ens permeti comparar objectes de diferent manera a l’estàndard.

·         Pel que fa als productes Textils, no podrem tenir dos productes al carret de la compra amb el mateix codi de barres i a més s’haurà d’ordenar segons la seva composició.

·         Ens demanen de forma més explícita una funció que cerqui el nom del producte pel codi de barres. Per a simplificar i millorar el codi, farem servir streams combinat amb expressions lambda.

Sobre les Excepcions:

Coming soon...!!!!
