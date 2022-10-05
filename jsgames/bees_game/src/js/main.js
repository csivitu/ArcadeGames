var spielfeld;
var takt;

var x = 0;
var y = 0;

var zielx;
var ziely;

var siegpunkte = 0;
var gegnerpunkte = 0;

var RandomInt = () => {
    return Math.floor(Math.random() * 28) * 20 + 20;
};

var zielfeld = new Image();
var spielfigur = new Image();
var humangegnerfigur = new Image();

var restzeit = 0;
var startzeit = new Date();

var taktbool = false;
var isGegnerOnZielTaktBool = false;

// Gegner
var gegnerpos = [];
var gegnerbew = [
    2,
    3,
    -2,
    4,
    5,
    -3
];
var gegnerbild = [];
var humangegner = {
    y: 0,
    x: 0
}

const config = {
    spielzeit: 45,
    gegnerzahl: 6,
    spielerbew: 20,
    gegnerbew: 20,
    gegnergesw: 300
}


window.addEventListener("load", function () {
    takt = window.setInterval(taktung, 1);
    gegnentakt = window.setInterval(GegnerTakt, config.gegnergesw);

    var spielbrett = document.getElementById('leinwand');
    spielfeld = spielbrett.getContext("2d");

    resetGame();
    function resetGame() {
        taktbool = true;
        isGegnerOnZielTaktBool = true;
        x = 0;
        y = 0;
        siegpunkte = 0;
        insertPunkte(0, siegpunkte)
        insertPunkte(1, gegnerpunkte);
        startzeit = new Date();
        restzeit = 0;
        ErzeugeGegner();
        DrawNewZiel();
        ErzeugeGegnerPos();
        setHumanGegner();
    }

    function taktung() {
        if (taktbool === true) {
            Init();
            zielfelderreicht();
            RestlicheZeit();
            kollisionspruefungGegner();
        }
    }
    function GegnerTakt() {
        if (taktbool === true) {
            setzeGegner();
            spielfeld.clearRect(0, 0, 600, 480);
        }
    }

    function Init() {
        spielfigur.src = 'src/img/spielfigur.png';
        spielfigur.addEventListener('load', function () {
            spielfeld.drawImage(spielfigur, x, y);
        });

        zielfeld.src = 'src/img/zielbereich.png';
        zielfeld.addEventListener("load", function () {
            spielfeld.drawImage(zielfeld, zielx, ziely);
        });

        humangegnerfigur.src = 'src/img/humangegner.png';
        humangegnerfigur.addEventListener('load', () => spielfeld.drawImage(humangegnerfigur, humangegner.x, humangegner.y));

        ErzeugeGegner();
    }

    function ErzeugeGegnerPos() {
        for (let i = 0; i < config.gegnerzahl; i++) {
            gegnerpos[i] = RandomInt();
        }
    }

    function setzeGegner() {
        for (i = 0; i < config.gegnerzahl; i ++) { // i wird hoch gezählt
            gegnerpos[i] += gegnerbew[i] * 5;
            if (gegnerpos[i] > 580 || gegnerpos[i] < 0) {
                gegnerbew[i] *= -1; // bewegung umkehren
            }spielfeld.drawImage(gegnerbild[i], gegnerpos[i], 360 - (i * 40));
        }
    }
    function setHumanGegner() {
        humangegner.x = RandomInt();
        humangegner.y = RandomInt();
        (humangegner.y < 50) ? humangegner.y = RandomInt() : (humangegner.x < 50) ? humangegner.x = RandomInt() : '';
    }

    function RestlicheZeit() {
        var aktuellezeit = new Date();
        restzeit = config.spielzeit - Math.floor(aktuellezeit.getTime() - startzeit.getTime()) / 1000;
        document.getElementById('spielzeit').innerHTML = parseInt(restzeit);
        (restzeit <= 0) ? spielende(`<span>Die Zeit ist abgelaufen! <br /> Deine Punkte sind ${siegpunkte}</span>`) : '';
    }

    function ErzeugeGegner() {
        for (let i = 0; i < config.gegnerzahl; i++) {
            var gegnerimg = new Image();
            gegnerimg.src = 'src/img/gegnerfigur.png';
            gegnerbild[i] = gegnerimg;
            gegnerimg.addEventListener('load', () => spielfeld.drawImage(gegnerimg, gegnerpos[i], 360 - (i * 40)));
        }
    }

    function DrawNewZiel() {
        zielx = RandomInt();
        ziely = RandomInt();

        (zielx >= 400 || zielx == undefined) ? zielx = RandomInt() : '';
        (ziely >= 400 || ziely == undefined) ? ziely = RandomInt() : '';
    }


    function spielende(message) {
        document.getElementById('Message').style.display = 'block';
        if (document.getElementById('Message').innerHTML =
            `${message}` + `<button id="playagain" style="position: absolute; top: 54%; left: 47.5%">Spiel nochmal </button>`) {
            document.getElementById('playagain').addEventListener('click', () => {
                document.getElementById('Message').style.display = 'none';
                document.getElementById('Message').innerHTML = "";
                resetGame();
            });
        }
        taktbool = false;
    }

    function insertPunkte(type, punkte) {
        console.log('%c%s', 'color: #917399', punkte);
        (type === 0) ? document.getElementById('punktestand').innerHTML = punkte : '';

        (type === 1) ? document.getElementById('punktestand_gegner').innerHTML = punkte : ''
    }

    function zielfelderreicht() {
        if (x == zielx && y == ziely) {
            siegpunkte++
            insertPunkte(0, siegpunkte)
            DrawNewZiel();
        }
    }

    function kollisionspruefungGegner() {
        for (i = 0; i < config.gegnerzahl; i ++) { // jeder muss geprueft werden
            var ygeg = 360 - (i * 40);
            (Math.abs(x - gegnerpos[i]) < 20 && y == ygeg) ? spielende(`<span>Du bist in einen Gegner gelaufen <br /> Deine Punkte sind ${siegpunkte}</span>`) : '';
        }
        (humangegner.x === x && humangegner.y === y) ? spielende(`<span>Dein Mitspieler hat dich gefasst! <br /> Deine Punkte sind ${siegpunkte}</span>`) : '';

    }


    var gegnerOnZielTimer = 1;
    function isGegnerOnZiel() {
        if (isGegnerOnZielTaktBool === true) {
            if (gegnerOnZielTimer < 2) {
                gegnerOnZielTimer++;
            } else {
                gegnerOnZielTimer++;
                DrawNewZiel();
                gegnerpunkte++;
                insertPunkte(1, gegnerpunkte);
                gegnerOnZielTimer = 1
                isGegnerOnZielTaktBool = false;
            }
        }
    }

    window.addEventListener("keydown", function (evt) {
        console.log(evt.keyCode);
        switch (evt.keyCode) {
            case 40: // unten
                y += config.spielerbew;
                break;
            case 38: // oben
                y -= config.spielerbew;
                break;
            case 39: // rechts
                x += config.spielerbew;
                break;
            case 37: // Nach links
                x -= config.spielerbew;
                break;

                // ! GEGNER
            case 87: // W
                humangegner.y -= config.gegnerbew;
                break;
            case 65: humangegner.x -= config.gegnerbew;
                break;
            case 83: humangegner.y += config.gegnerbew;
                break;
            case 68: humangegner.x += config.gegnerbew;

        }(y >= 480) ? y = 0 : (y < 0) ? y = 459 : '';
        (x >= 590) ? x = 0 : (x < 0) ? x = 579 : '';
        (humangegner.y >= 470) ? humangegner.y = 0 : (humangegner.y < 0) ? humangegner.y = 459 : '';
        (humangegner.x >= 600) ? humangegner.x = 0 : (humangegner.x < 0) ? humangegner.x = 579 : '';

        if (zielx === humangegner.x && ziely === humangegner.y) {
            isGegnerOnZielTaktBool = true;
            window.setInterval(isGegnerOnZiel, 2000);
        }

        spielfeld.clearRect(0, 0, 600, 480);
    });

});
