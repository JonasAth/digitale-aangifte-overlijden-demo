# Gebruik van de API van het DAO-platform voor het vaststellen van een overlijden

## Inleiding

Dit document legt uit hoe je de API van het DAO-platform kunt gebruiken om een overlijden te registreren.

Voor meer info omtrent de security kan je [hier meer lezen](../../../algemeen/security/README.md)

## Endpoints

Alle endpoints buiten de context starten altijd met: `/vaststelling/v1`

### Json-ld context

Alle requests moeten voldoen aan het JSON-ld formaat met behulp van de volgende contextbestanden:
- **Contextbestand**: `/context/AangifteOverlijden.jsonld`
- **Contextbestand**: `/context/VerslagBeedigdArts.jsonld`

### Vaststelling van een overlijden van een persoon ouder dan 1 jaar
- **Endpoint**: `/vaststelling/ouder-dan-1-jaar`
- **Beschrijving**: Gebruik dit endpoint om een overlijden te registreren van een persoon die ouder is dan 1 jaar.

### Vaststelling van een overlijden van een persoon tot 1 jaar
- **Endpoint**: `/vaststelling/jonger-dan-1-jaar`
- **Beschrijving**: Gebruik dit endpoint om een overlijden te registreren van een kind jonger dan 1 jaar, of voor een doodgeboorte.

### Verslag van een beëdigd arts indienen
- **Endpoint**: `/verslag-beedigd-arts`
- **Beschrijving**: Gebruik dit endpoint om een verslag van een beëdigd arts door te geven.

## Swagger

De volledige documentatie van de API is beschikbaar via Swagger-UI. Deze is te vinden op:
- **Locatie**: `/swagger-ui/index.html`
- [**Test**](https://dao.api.test-athumi.eu/swagger-ui/index.html?urls.primaryName=Vaststelling)
- [**Beta**](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Vaststelling)


## Validatiefouten

Als er validatiefouten optreden bij het versturen van een request, zal de API een HTTP 400-fout terugsturen. 
