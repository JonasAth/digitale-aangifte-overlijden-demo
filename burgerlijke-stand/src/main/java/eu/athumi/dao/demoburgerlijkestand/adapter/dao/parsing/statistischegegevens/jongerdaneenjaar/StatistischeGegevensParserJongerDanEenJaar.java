package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.jongerdaneenjaar;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.socioeconomische.*;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.PersoonsgegevensDepartementZorgJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.vaststelling.MoederVaststellingJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.vaststelling.PersoonsgegevensVaststellingJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.StatistischeGegevensJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder.MoederOfOudsteOuderJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder.OudersJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene.OverledeneJongerDanEenJaarJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.rijksregisternummers.RijksregisternummerPersoonJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.rijksregisternummers.RijksregisternummersJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.rijksregisternummers.RijksregisternummersJongerDanEenJaarJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.statistischegegevens.TableRow;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public record StatistischeGegevensParserJongerDanEenJaar(StatistischeGegevensJSON statistischeGegevens) {
    static final String DASH = "-";
    private PersoonsgegevensDepartementZorgJSON getPersoonsGegevensVoorDepartementZorg() {
        return statistischeGegevens().persoonsgegevens().departementZorg();
    }

    private OverledeneJongerDanEenJaarJSON getOverledeneVoorDepartementZorg() {
        return (OverledeneJongerDanEenJaarJSON) getPersoonsGegevensVoorDepartementZorg().overledene();
    }

    private Optional<PersoonsgegevensVaststellingJSON> getPersoonsGegevensVoorVaststelling() {
        return Optional.ofNullable(statistischeGegevens().persoonsgegevens().vaststelling());
    }

    private Optional<OverledeneJongerDanEenJaarJSON> getOverledeneVoorVaststelling() {
        return getPersoonsGegevensVoorVaststelling()
                .map(PersoonsgegevensVaststellingJSON::overledene)
                .map(o -> (OverledeneJongerDanEenJaarJSON) o);
    }

    private OudersJSON getOudersVoorDepartementZorg() {
        return getPersoonsGegevensVoorDepartementZorg().ouders();
    }

    private MoederVaststellingJSON getMoederVoorVaststelling() {
        return getPersoonsGegevensVoorVaststelling().map(PersoonsgegevensVaststellingJSON::moeder).orElse(null);
    }

    public OverledenenParser overledene() {
        return new OverledenenParser(statistischeGegevens(), getOverledeneVoorDepartementZorg(), getOverledeneVoorVaststelling());
    }

    public OuderParser ouder1() {
        return new OuderParser(getOudersVoorDepartementZorg().moederOfOudsteOuder(), getMoederVoorVaststelling());
    }

    public OuderParser ouder2() {
        return new OuderParser(getOudersVoorDepartementZorg().vaderOfJongsteOuder(), null);
    }

    public TableRow rrnOverledene() {
        return new TableRow(
                "RRN overledenen",
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJSON::overledene).map(RijksregisternummerPersoonJSON::rijksregisternummerRijksregister).orElse("-"),
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJSON::overledene).map(RijksregisternummerPersoonJSON::rijksregisternummerVaststelling).orElse("-"),
                "-",
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJSON::overledene).map(RijksregisternummerPersoonJSON::rijksregisternummerLokaalBestuur).orElse("-"),
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJSON::overledene).map(RijksregisternummerPersoonJSON::rijksregisternummerDepartementZorg).orElse("-")
        );
    }

    public TableRow rrnMoeder() {
        return new TableRow(
                "RRN moeder",
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJongerDanEenJaarJSON.class::cast).map(RijksregisternummersJongerDanEenJaarJSON::moederOfOudsteOuder).map(RijksregisternummerPersoonJSON::rijksregisternummerRijksregister).orElse("-"),
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJongerDanEenJaarJSON.class::cast).map(RijksregisternummersJongerDanEenJaarJSON::moederOfOudsteOuder).map(RijksregisternummerPersoonJSON::rijksregisternummerVaststelling).orElse("-"),
                "-",
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJongerDanEenJaarJSON.class::cast).map(RijksregisternummersJongerDanEenJaarJSON::moederOfOudsteOuder).map(RijksregisternummerPersoonJSON::rijksregisternummerLokaalBestuur).orElse("-"),
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJongerDanEenJaarJSON.class::cast).map(RijksregisternummersJongerDanEenJaarJSON::moederOfOudsteOuder).map(RijksregisternummerPersoonJSON::rijksregisternummerDepartementZorg).orElse("-")
        );
    }

    public TableRow rrnVaderOfMoeder() {
        return new TableRow(
                "RRN vader/meemoeder",
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJongerDanEenJaarJSON.class::cast).map(RijksregisternummersJongerDanEenJaarJSON::vaderOfJongsteOuder).map(RijksregisternummerPersoonJSON::rijksregisternummerRijksregister).orElse("-"),
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJongerDanEenJaarJSON.class::cast).map(RijksregisternummersJongerDanEenJaarJSON::vaderOfJongsteOuder).map(RijksregisternummerPersoonJSON::rijksregisternummerVaststelling).orElse("-"),
                "-",
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJongerDanEenJaarJSON.class::cast).map(RijksregisternummersJongerDanEenJaarJSON::vaderOfJongsteOuder).map(RijksregisternummerPersoonJSON::rijksregisternummerLokaalBestuur).orElse("-"),
                ofNullable(statistischeGegevens.rijksregisternummers()).map(RijksregisternummersJongerDanEenJaarJSON.class::cast).map(RijksregisternummersJongerDanEenJaarJSON::vaderOfJongsteOuder).map(RijksregisternummerPersoonJSON::rijksregisternummerDepartementZorg).orElse("-")
        );
    }


    /*
    * TODO: BABY MAMA PAPA
    *  */
    private Optional<SEG> seg() {
        return Optional.ofNullable(statistischeGegevens).map(StatistischeGegevensJSON::socioeconomischegegevens);
    }
    private String parseOpleiding(Opleiding seg) {
        if (seg != null) {
            if(seg.opleidingAndere() != null && !seg.opleidingAndere().isBlank()) {
                return seg.opleidingAndere();
            } else {
                var extraInfo = Optional.ofNullable(seg.onderwijsType()).map(OnderwijsType::getLabel).orElse("");
                return seg.type().getLabel() + " " + extraInfo;
            }
        } return DASH;
    }
    public TableRow opleidingMama() {
        return new TableRow(
                "Hoogst voltooide opleiding",
                DASH,
                DASH,
                seg().map(s -> s.uitvaart()).map(u -> u.moeder()).map(o -> o.opleiding()).map(this::parseOpleiding).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.moeder()).map(o -> o.opleiding()).map(this::parseOpleiding).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.moeder()).map(o -> o.opleiding()).map(this::parseOpleiding).orElse(DASH)
        );
    }
    public TableRow opleidingPapa() {
        return new TableRow(
                "Hoogst voltooide opleiding",
                DASH,
                DASH,
                seg().map(s -> s.uitvaart()).map(u -> u.vaderOfMeemoeder()).map(o -> o.opleiding()).map(this::parseOpleiding).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.vaderOfMeemoeder()).map(o -> o.opleiding()).map(this::parseOpleiding).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.vaderOfMeemoeder()).map(o -> o.opleiding()).map(this::parseOpleiding).orElse(DASH)
        );
    }

    private String parseBeroepstoestand(Beroepstoestand seg) {
        if (seg != null) {
            if(seg.beroepstoestandAndere() != null && !seg.beroepstoestandAndere().isBlank()) {
                return seg.beroepstoestandAndere();
            } else {
                return String.valueOf(seg.type());
            }
        } return DASH;
    }
    public TableRow beroepstoestandMama() {
        return new TableRow(
                "Huidige beroepstoestand",
                DASH,
                DASH,
                seg().map(s -> s.uitvaart()).map(u -> u.moeder()).map(o -> o.beroepstoestand()).map(this::parseBeroepstoestand).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.moeder()).map(o -> o.beroepstoestand()).map(this::parseBeroepstoestand).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.moeder()).map(o -> o.beroepstoestand()).map(this::parseBeroepstoestand).orElse(DASH)
        );
    }
    public TableRow beroepstoestandPapa() {
        return new TableRow(
                "Huidige beroepstoestand",
                DASH,
                DASH,
                seg().map(s -> s.uitvaart()).map(u -> u.vaderOfMeemoeder()).map(o -> o.beroepstoestand()).map(this::parseBeroepstoestand).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.vaderOfMeemoeder()).map(o -> o.beroepstoestand()).map(this::parseBeroepstoestand).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.vaderOfMeemoeder()).map(o -> o.beroepstoestand()).map(this::parseBeroepstoestand).orElse(DASH)
        );
    }

    private String parseSocialeStaat(SocialeStaat seg) {
        if (seg != null) {
            if(seg.socialeStaatAndere() != null && !seg.socialeStaatAndere().isBlank()) {
                return seg.socialeStaatAndere();
            } else {
                return String.valueOf(seg.type());
            }
        } return DASH;
    }
    public TableRow socialestaatMama() {
        return new TableRow(
                "Sociale staat huidig beroep",
                DASH,
                DASH,
                seg().map(s -> s.uitvaart()).map(u -> u.moeder()).map(o -> o.socialeStaat()).map(this::parseSocialeStaat).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.moeder()).map(o -> o.socialeStaat()).map(this::parseSocialeStaat).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.moeder()).map(o -> o.socialeStaat()).map(this::parseSocialeStaat).orElse(DASH)
        );
    }
    public TableRow socialestaatPapa() {
        return new TableRow(
                "Sociale staat huidig beroep",
                DASH,
                DASH,
                seg().map(s -> s.uitvaart()).map(u -> u.vaderOfMeemoeder()).map(o -> o.socialeStaat()).map(this::parseSocialeStaat).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.vaderOfMeemoeder()).map(o -> o.socialeStaat()).map(this::parseSocialeStaat).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.vaderOfMeemoeder()).map(o -> o.socialeStaat()).map(this::parseSocialeStaat).orElse(DASH)
        );
    }
    public TableRow beroepMama() {
        return new TableRow(
                "Huidig beroep (laatst uitgeoefend)",
                DASH,
                DASH,
                seg().map(s -> s.uitvaart()).map(u -> u.moeder()).map(o -> o.beroepstoestand()).map(b -> b.beroepen()).map(List::getFirst).map(b -> b.omschrijving()).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.moeder()).map(o -> o.beroepstoestand()).map(b -> b.beroepen()).map(List::getFirst).map(b -> b.omschrijving()).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.moeder()).map(o -> o.beroepstoestand()).map(b -> b.beroepen()).map(List::getFirst).map(b -> b.omschrijving()).orElse(DASH)
        );
    }
    public TableRow beroepPapa() {
        return new TableRow(
                "Huidig beroep (laatst uitgeoefend)",
                DASH,
                DASH,
                seg().map(s -> s.uitvaart()).map(u -> u.vaderOfMeemoeder()).map(o -> o.beroepstoestand()).map(b -> b.beroepen()).map(List::getFirst).map(b -> b.omschrijving()).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.vaderOfMeemoeder()).map(o -> o.beroepstoestand()).map(b -> b.beroepen()).map(List::getFirst).map(b -> b.omschrijving()).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.vaderOfMeemoeder()).map(o -> o.beroepstoestand()).map(b -> b.beroepen()).map(List::getFirst).map(b -> b.omschrijving()).orElse(DASH)
        );
    }
    private String parseGezinstoestand(Gezinstoestand seg) {
        if (seg != null) {
            if(seg.gezinstoestandAndere() != null && !seg.gezinstoestandAndere().isBlank()) {
                return seg.gezinstoestandAndere();
            } else {
                return String.valueOf(seg.type());
            }
        } return DASH;
    }
    public TableRow gezinstoestand() {
        return new TableRow(
                "Gezinstoestand",
                DASH,
                DASH,
                seg().map(s -> s.uitvaart()).map(u -> u.moeder()).map(o -> o.gezinstoestand()).map(this::parseGezinstoestand).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.moeder()).map(o -> o.gezinstoestand()).map(this::parseGezinstoestand).orElse(DASH),
                seg().map(s -> s.lokaalBestuur()).map(l -> l.moeder()).map(o -> o.gezinstoestand()).map(this::parseGezinstoestand).orElse(DASH)
        );
    }
}
