// src/main/resources/static/app.js

const App = {};

/**
 * Session-ID:
 * - wird einmal erzeugt
 * - in localStorage gespeichert
 * - bei jedem Request wiederverwendet
 */
App.getSessionId = function () {
    let s = localStorage.getItem("sessionID");
    if (!s) {
        s = "sess-" + Date.now() + "-" + Math.random().toString(36).substring(2, 8);
        localStorage.setItem("sessionID", s);
    }
    return s;
};

/**
 * BFI-10 lokal speichern / laden
 */
App.saveBfi = function (data) {
    localStorage.setItem("bfi", JSON.stringify(data));
};

App.loadBfi = function () {
    const raw = localStorage.getItem("bfi");
    return raw ? JSON.parse(raw) : null;
};

/**
 * Gesamtergebnis (Response von /api/submit-all) lokal speichern / laden
 */
App.saveResult = function (result) {
    localStorage.setItem("result", JSON.stringify(result));
};

App.loadResult = function () {
    const raw = localStorage.getItem("result");
    return raw ? JSON.parse(raw) : null;
};
