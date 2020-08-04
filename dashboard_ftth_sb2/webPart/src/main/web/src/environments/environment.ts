// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  url: "http://bt1svuzpg0.bpa.bouyguestelecom.fr:8090/DashboardFtth/",
  production: true,
  isResynchInProgress: false,
  resynchroDate: "",
  refreshTimer: 300000,
  resynchTimer: 5000,
  resynchRefresh: 500,
  clearMessageTimer: 20000,
  listNbPages: [10, 25, 50, 100],
  colorGood: "#A0FE7D",
  colorWarning: "#FEEE7D",
  colorAlerte: "#F73229",
  thresholdWarning: 0,
  thresholdAlerte: 10,
  columnDetailHsNameId: [{ id: "region", name: "REGION", valeur: "", rule: "", type: "string" }, { id: "idPm", name: "ID PM", valeur: "", rule: "", type: "string" }, { id: "partenaireAdduction", name: "PARTENAIRE ADDUCTION", valeur: "", rule: "", type: "string" }, { id: "creationDate", name: "DATE CREATION", valeur: "", rule: "", type: "string" }, { id: "nbClients", name: "NB CLIENTS", valeur: "", rule: "", type: "string" }, { id: "nbClientsHs", name: "NB CLIENTS HS", valeur: "", rule: "", type: "string" }, { id: "osp", name: "OLT-SLOT-PORT", valeur: "", rule: "", type: "string" }, { id: "gcr", name: "GCR", valeur: "", rule: "", type: "string" }, { id: "tt", name: "TT", valeur: "", rule: "", type: "string" }],
  columnDetailOkNameId: [{ id: "region", name: "REGION", valeur: "", rule: "", type: "string" }, { id: "idPm", name: "ID PM", valeur: "", rule: "", type: "string" }, { id: "partenaireAdduction", name: "PARTENAIRE ADDUCTION", valeur: "", rule: "", type: "string" }, { id: "creationDate", name: "DATE CREATION", valeur: "", rule: "", type: "string" }, { id: "nbClients", name: "NB CLIENTS", valeur: "", rule: "", type: "string" }, { id: "nbClientsHs", name: "NB CLIENTS HS", valeur: "", rule: "", type: "string" }, { id: "osp", name: "OLT-SLOT-PORT", valeur: "", rule: "", type: "string" }, { id: "gcr", name: "GCR", valeur: "", rule: "", type: "string" }, { id: "tt", name: "TT", valeur: "", rule: "", type: "string" }, { id: "resolutionDate", name: "DATE RESOLUTION", valeur: "", rule: "", type: "string" }],
  columnGlobalNameId: [{ id: "region", name: "REGION", valeur: "", rule: "", type: "string" }, { id: "siteNro", name: "SITE NRO", valeur: "", rule: "", type: "string" }, { id: "partenaire", name: "PARTENAIRE", valeur: "", rule: "", type: "string" }, { id: "id", name: "OLT", valeur: "", rule: "", type: "string" }, { id: "pmHs", name: "PM HS", valeur: "", rule: "", type: "number" }, { id: "pmOk", name: "PM OK", valeur: "", rule: "", type: "number" }],

  orderDetailHs: [{ column: "region", direction: "asc", type: "string" }, { column: "idPm", direction: "asc", type: "string" }, { column: "osp", direction: "asc", type: "string" }],
  orderDetailOk: [{ column: "region", direction: "asc", type: "string" }, { column: "idPm", direction: "asc", type: "string" }, { column: "osp", direction: "asc", type: "string" }],
  orderGlobal: [{ column: "region", direction: "asc", type: "string" }],
  selectionText: ["Contient", "Egal", "Different", "Retirer le filtre"],
  selectionNumber: ["<", "=", ">", "Retirer le filtre"],
  globalData: [
    {
      "region": null,
      "siteNro": "SI41687",
      "partenaire": "sfr",
      "id": "OLT2342",
      "pmHs": 4,
      "pmOk": 564
    },
    {
      "region": "WST",
      "siteNro": "SI55555",
      "partenaire": "orange",
      "id": "OLT0104",
      "pmHs": 69,
      "pmOk": 65
    },
    {
      "region": "IDF",
      "siteNro": "SI12345",
      "partenaire": "bytel",
      "id": "OLT2531",
      "pmHs": 1,
      "pmOk": 1
    }
  ],
  dataDetail: {
    "pmHs": [],
    "pmOk": [
      { "region": "WST", "idPm": "SF-ST-FTLP9101", "partenaireAdduction": "SAFR", "creationDate": null, "nbClients": 5, "nbClientsHs": 0, "osp": "OLT2955-1-4", "gcr": null, "tt": null, "resolutionDate": null },
      { "region": "CTA", "idPm": "SF-SaT-FTLP0101", "partenaireAdduction": "Orange", "creationDate": null, "nbClients": 4, "nbClientsHs": 0, "osp": "OLT2955-1-5", "gcr": null, "tt": null, "resolutionDate": null },
      { "region": "WST", "idPm": "SdF-ST-FTLP0101", "partenaireAdduction": "orange", "creationDate": null, "nbClients": 3, "nbClientsHs": 0, "osp": "OLT2955-1-6", "gcr": null, "tt": null, "resolutionDate": null }]
  },

};
