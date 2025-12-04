// app.js – zentrale Logik für Session, Speicherung & Datenaustausch

(function () {

    const KEY = {
        SESSION: "survey_session_id",
        BFI: "survey_bfi_data",
        RESULT: "survey_result_data"
    };

    // 🔹 erzeugt Session-ID, wenn nicht vorhanden – sonst holt sie
    function getSessionId() {
        let id = sessionStorage.getItem(KEY.SESSION);
        if (!id) {
            id = "sess-" + Date.now() + "-" + Math.random().toString(36).slice(2,10);
            sessionStorage.setItem(KEY.SESSION, id);
        }
        return id;
    }

    // 🔹 Big Five Zwischenspeicherung
    function saveBfi(data) {
        sessionStorage.setItem(KEY.BFI, JSON.stringify(data));
    }
    function loadBfi() {
        const d = sessionStorage.getItem(KEY.BFI);
        return d ? JSON.parse(d) : null;
    }

    // 🔹 Ergebnisdatenspeicherung (nach POST)
    function saveResult(data) {
        sessionStorage.setItem(KEY.RESULT, JSON.stringify(data));
    }
    function loadResult() {
        const d = sessionStorage.getItem(KEY.RESULT);
        return d ? JSON.parse(d) : null;
    }

    // **global nutzbar machen**
    window.App = {
        getSessionId,
        saveBfi,
        loadBfi,
        saveResult,
        loadResult
    };

})();
