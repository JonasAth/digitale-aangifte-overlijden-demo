# Aanvragen Client

Voor het aanvragen van de client kan je gebruik maken van de documentatie van het [Beheerportaal Vlaanderen](https://vlaamseoverheid.atlassian.net/wiki/spaces/IKPubliek/pages/6282739963/Uw+OAuth-client+beheren+via+het+ACM-Beheerportaal).

Met als referentie: `DAO (Digitale Aangifte Overlijden) API T&I` of `DAO (Digitale Aangifte Overlijden) API`  

Met als client name: `DAO [omgeving] <organisatie> client` 
hierbij is de omgeving een van de volgende waardes: beta/acc/PROD 
en organisatie: De organisatie waarvoor de client wordt aangemaakt. 

// TODO: Opkuis
Stap 1: registratie DAO API Client
 
Een eerste stap is een client aanmaken in ACM/IDM en hiervoor toegang tot onze DAO API te vragen.
 
Voor het Departement Zorg vraagt u volgende scope aan: dao_depzorg
 
Het is belangrijk en uw verantwoordelijkheid om correct om te gaan met gebruikersrollen en applicatie scopes in deze client. Gebruikers mogen enkel toegang krijgen tot de toepasselijke functionaliteit van het DAO platform. 
 
Praktisch
 
Ga naar het beheerportaal: https://beheerportaal-ti.vlaanderen.be
 
De API waarvoor u toegang moet vragen heeft volgende kenmerken
referentie = DAO (Digitale Aangifte Overlijden) API T&I
client id = b3f43c85-53a5-443d-838c-7c2d0359897b
Er wordt verwacht dit te doen via
het  OAuth2 Client Credentials Grant (CCG) protocol
met authenticatie via een JWT
conventie client naam: DAO beta <organisatie> client
Relevante ACM/IDM gebruikersinformatie vindt u op de website van het beheerportaal van de Vlaamse overheid:
algemeen rond het beheer van API client met OAuth Client Credentials Grant
Module OAuth Client Credentials Grant: API-Client beheren - Gebruikersomgeving Applicatie- en platformdiensten - Confluence (atlassian.net)
specifiek over creatie van nieuwe API clients
Nieuwe OAuth client aanmaken
Voorgaande info zou u moeten toelaten om de client aan te maken en de aanvraag te sturen naar ons.
Gelieve ons een mailtje terug te sturen wanneer u bovenstaande client heeft aangevraagd. Zo kunnen wij dit goedkeuren.
 
 
Stap 2: probeer een eerste keer connectie te maken met onze API via ACM/IDM T&I
 
We hebben volgend "ping" endpoint voorzien 
 
      https://dao.api.beta-athumi.eu/health/v1/ping
 
dat u kan gebruiken om te testen of de registratie van uw client correct is verlopen.
 
Bij succesvolle aanmelding en ping call zal u als respons een JSON terug krijgen dat er ongeveer als volgt uitziet.
 
Image
 
De vo_orgcode, de vo_orgnaam en de client id moeten herkenbaar zijn en kloppen met uw client.
