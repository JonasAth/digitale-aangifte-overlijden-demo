package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.NationaliteitJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.PersoonsgegevensVaststellingJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.StatistischeGegevensJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat.BurgerlijkeStaatJSONType;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.burgerlijkeStaat.HuwelijkOverledeneJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.AdresJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.GemeenteJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.Plaats;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.PlaatsTypeJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene.OverledeneOuderDanEenJaarJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overlijdensgegevens.AdresStatistischJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overlijdensgegevens.OverlijdenStatistischJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overlijdensgegevens.OverlijdensgegevensJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser;

import java.util.Optional;

import static eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser.parseLocalDate;
import static java.util.Optional.ofNullable;

public record StatistischeGegevensParserOuderDanEenJaar(StatistischeGegevensJSON statistischeGegevens) {

    private OverledeneOuderDanEenJaarJSON getOverledeneVoorDepartementZorg() {
        return (OverledeneOuderDanEenJaarJSON) statistischeGegevens().persoonsgegevens().departementZorg().overledene();
    }

    private Optional<OverledeneOuderDanEenJaarJSON> getOverledeneVoorVaststelling() {
        return Optional.ofNullable(statistischeGegevens().persoonsgegevens().vaststelling())
                .map(PersoonsgegevensVaststellingJSON::overledene)
                .map(v -> (OverledeneOuderDanEenJaarJSON) v);
    }

    private HuwelijkOverledeneJSON getHuwelijkOverledene() {
        return (HuwelijkOverledeneJSON) getOverledeneVoorDepartementZorg().huwelijk();
    }

    private HuwelijkOverledeneJSON getHuwelijkOverledeneVaststelling() {
        return (HuwelijkOverledeneJSON) getOverledeneVoorVaststelling()
                .map(OverledeneOuderDanEenJaarJSON::huwelijk)
                .orElse(null);
    }

    public Optional<OverlijdenStatistischJSON> overlijden() {
        return overlijdensgegevens().map(OverlijdensgegevensJSON::overlijden);
    }

    public Optional<OverlijdensgegevensJSON> overlijdensgegevens() {
        return Optional.ofNullable(statistischeGegevens.overlijdensgegevens());
    }

    public TableRow geslacht() {
        return new TableRow(
                "Geslacht",
                "-",
                getOverledeneVoorVaststelling().map(OverledeneOuderDanEenJaarJSON::geslacht).map(Geslacht::name).orElse("-"),
                "-",
                "-",
                ofNullable(getOverledeneVoorDepartementZorg().geslacht()).map(Geslacht::name).orElse("-")
        );
    }

    public TableRow tijdstipOverlijden() {
        return new TableRow(
                "Tijdstip overlijden",
                "-",
                overlijden().map(OverlijdenStatistischJSON::tijdstip).map(TijdstipParser::parseLocalDateTime).or(() -> overlijden().map(OverlijdenStatistischJSON::beschrijvingTijdstip)).orElse("-"),
                "-",
                "-",
                overlijden().map(OverlijdenStatistischJSON::tijdstip).map(TijdstipParser::parseLocalDateTime).or(() -> overlijden().map(OverlijdenStatistischJSON::beschrijvingTijdstip)).orElse("-")
        );
    }

    public TableRow gemeenteOverlijden() {
        return new TableRow(
                "Gemeente van overlijden",
                "-",
                overlijden().map(OverlijdenStatistischJSON::adres).map(AdresStatistischJSON::gemeente).map(GemeenteJSON::niscode).orElse("-"),
                "-",
                "-",
                overlijden().map(OverlijdenStatistischJSON::adres).map(AdresStatistischJSON::gemeente).map(GemeenteJSON::niscode).orElse("-")
        );
    }

    public TableRow plaatsOverlijden() {
        return new TableRow(
                "Plaats van overlijden",
                "-",
                overlijden().flatMap(this::parsePlaats).orElse("-"),
                "-",
                "-",
                overlijden().flatMap(this::parsePlaats).orElse("-")
        );
    }

    public TableRow geboorteDatum() {
        return new TableRow(
                "Geboortedatum",
                "-",
                getOverledeneVoorVaststelling().map(OverledeneOuderDanEenJaarJSON::geboorte).map(geboorte -> parseLocalDate(geboorte.datum())).orElse("-"),
                "-",
                "-",
                ofNullable(getOverledeneVoorDepartementZorg().geboorte()).map(geboorte -> parseLocalDate(geboorte.datum())).orElse("-")
        );
    }

    public TableRow nationaliteit() {
        return new TableRow(
                "Nationaliteit",
                "-",
                getOverledeneVoorVaststelling().map(OverledeneOuderDanEenJaarJSON::nationaliteit).map(NationaliteitJSON::naam).orElse("-"),
                "-",
                "-",
                ofNullable(getOverledeneVoorDepartementZorg().nationaliteit()).map(NationaliteitJSON::naam).orElse("-")
        );
    }

    public TableRow verblijfplaats() {
        return new TableRow(
                "Verblijfplaats",
                "-",
                getOverledeneVoorVaststelling().map(OverledeneOuderDanEenJaarJSON::verblijfplaats).map(AdresJSON::gemeente).map(GemeenteJSON::niscode).orElse("-"),
                "-",
                "-",
                ofNullable(getOverledeneVoorDepartementZorg().verblijfplaats()).map(AdresJSON::gemeente).map(GemeenteJSON::niscode).orElse("-")
        );
    }

    public TableRow burgerlijkeStaat() {
        return new TableRow(
                "Burgerlijke staat",
                "-",
                getOverledeneVoorVaststelling().map(OverledeneOuderDanEenJaarJSON::burgerlijkeStaat).map(BurgerlijkeStaatJSONType::name).orElse("-"),
                "-",
                "-",
                ofNullable(getOverledeneVoorDepartementZorg().burgerlijkeStaat()).map(BurgerlijkeStaatJSONType::name).orElse("-")
        );
    }

    public TableRow huwelijksDatum() {
        return new TableRow(
                "Datum huidig huwelijk",
                "-",
                ofNullable(getHuwelijkOverledeneVaststelling()).map(huwelijk -> parseLocalDate(huwelijk.datum())).orElse("-"),
                "-",
                "-",
                ofNullable(getHuwelijkOverledene()).map(huwelijk -> parseLocalDate(huwelijk.datum())).orElse("-")
        );
    }

    public TableRow geboorteDatumPartner() {
        return new TableRow(
                "Geboortedatum overlevende partner",
                "-",
                ofNullable(getHuwelijkOverledeneVaststelling()).map(huwelijk -> parseLocalDate(huwelijk.geboorteDatumOverlevendePartner())).orElse("-"),
                "-",
                "-",
                ofNullable(getHuwelijkOverledene()).map(huwelijk -> parseLocalDate(huwelijk.geboorteDatumOverlevendePartner())).orElse("-"));

    }

    private Optional<String> parsePlaats(Plaats plaats) {
        if (plaats.plaats() == PlaatsTypeJSON.ANDERE) {
            return Optional.ofNullable(plaats.plaatsBeschrijving()).map(beschrijving -> String.format("ANDERE: %s", beschrijving));
        }
        return Optional.ofNullable(plaats.plaats()).map(Enum::name);
    }
}
