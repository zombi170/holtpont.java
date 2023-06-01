# Fakultatív feladat: holtpontelkerülés

Készítsen egy programot Java vagy Python nyelven, amely erőforrás-allokációs gráf segítségével holtpontelkerülést valósít meg!

A szimulátor a standard input bemenetről fogadja taszkok nevét és utasításait (lásd lentebb), majd azok futtatásával szimulálja erőforrások allokációját. A program kimenete az elkerült holtpontok adatai (időrendben).

A szimuláció lépésekben történik. Minden lépésben minden taszk egy utasítást hajt végre (amennyiben nem várakozik egy erőforrásra). Az utasítás lehet erőforrás kérése, felszabadítása és "egyéb" művelet. Egy lépésen belül az utasítások végrehajtása a taszkok bemenetben meghatározott sorrendje szerint történik. Ha egy taszknak egy lépésben már nincs több végrehajtható művelete, akkor véget ér, és a lefoglalt állapotban maradt erőforrásai a lefoglalásaik sorrendjében felszabadulnak.

A szimulátor a taszkok műveletei alapján felépít egy erőforrás-allokációs gráfot, és annak segítségével minden foglalásnál ellenőrzi, hogy az holtponthoz vezet-e.   Amennyiben igen, úgy a foglalást visszautasítja. Az így visszautasított foglalási műveletet a taszk nem ismétli meg később, és nem is blokkolódik miatta; a következő lépésben a következő utasítását hajtja majd végre. Amennyiben egy taszk általa nem lefoglalt erőforrást szabadítana fel (pl. egy korábban visszautasított foglalása miatt), akkor semmi sem történik (mintha ott "0" szerepelne a bemenetén).

A feladat során egypéldányos erőforrásokat foglalhatnak a taszkok, így a holtpontot az erőforrás-allokációs gráf elemzésével lehet detektálni. Minden foglalási igény kiszolgálása előtt ellenőrizni kell, hogy az az egy lépés holtpontot okoz-e (azaz a gráfban kialakul-e kör a lépés után). Egy taszk erőforráskérése háromféle kimenetet eredményezhet: az erőforrás szabad és nincs holtpont (normál visszatérés); az erőforrás szabad, de holtpont alakulna ki (a szimulátor visszautasítja a kérést); és az erőforrás foglalt (a szimulátor a taszkot várakozó állapotba helyezi).

Egy erőforrás felszabadítása során a szimulátor az arra várakozó taszkok közül a FIFO-elv szerint választ, azaz a legrégebben várakozó taszk kapja meg az erőforrást, és az a következő alkalommal (amikor a szimulátor ezt a taszkot futtatja) végrehajtja majd a következő utasítását.

## Bemenet (standard input, stdin)
Soronként egy-egy taszk nevét és ütemenkénti utasításait tartalmazza a következők szerint:

T1, T2, T3, ... taszkok nevei (egy szóköz nélküli karakterfüzér)
R1, R2, R3, ... erőforrások nevei (egy szóköz nélküli karakterfüzér)
"+R1": erőforrás-foglalási kérés, "-R1": erőforrás-felszabadítási utasítás, "0" egyéb művelet
pl.: a "T1,+R1,0,0,+R2,-R1,-R2" sorozat értelmezése: a T1 taszk elsőként szeretné lefoglalni R1-et, azután két ütemben más tevékenységet végez, majd foglalási kérést ad ki R2-re, azután felszabadítja R1-et, végül R2-t is.

Egy teljes bemeneti példa:
```
T1,+R1,0,0,+R2,-R1,-R2
T2,+R2,+R1,-R1,-R2
T3,0,0,0,+R3,+R3,-R3,-R3
```
A bemenet végén soremelés, üres sor lehet.

## Kimenet (standard output, stdout)
A kimeneten soronként egy-egy elkerült holtpont részleteit kell megadni az alábbi formátumban:
taszk neve (amelyik a holtpontot okozná), a taszk sorrendben hányadik műveletét utasította el a szimulátor, erőforrás neve (amelyiknek a lefoglalása holtpontot okozott volna). Például:
```
T1,4,R2
T3,5,R3
```
A fenti példa értelmezése: a szimulátor két utasításnál detektált holtpontot: T1 4. utasítása (R2 erőforrás foglalási kérelme) és T3 5. utasítása (R3 foglalási kérelme) során.
