import java.io.IOException;

public class Client {
  public static void main(String[] args) throws IOException {
    String openCode = "1111";
    String fireAlarmCode = "2222";
    String unlockCode = "3333";

    // --- CHAIN 1: Fa tot (Log, Unlock, FireAlarm, Open, Lock) ---
    CodeHandler chain1 = new Log(
        new Unlock(unlockCode,
            new FireAlarm(fireAlarmCode,
                new Open(openCode,
                    new Lock(null)
                )
            )
        )
    );

    // --- CHAIN 2: Només Open i Log (sense bloqueig per intents fallits) ---
    CodeHandler chain2 = new Log(
        new Open(openCode, null)
    );

    // --- CHAIN 3: Open + FireAlarm només ---
    CodeHandler chain3 = new FireAlarm(
        fireAlarmCode,
        new Open(openCode, null)
    );

    System.out.println("=== PROVES AMB CHAIN 1 ===");
    Door d1 = new Door("d1", chain1);

    d1.processCode("1111"); // Obre
    d1.processCode("2222"); // Obre i dispara alarma
    d1.processCode("1234"); // Primer intent fallit
    d1.processCode("4321"); // Segon intent fallit
    d1.processCode("5555"); // Tercer intent fallit -> es bloqueja
    d1.processCode("6666"); // Intent invàlid amb porta bloquejada
    d1.processCode("3333"); // Codi de desbloqueig vàlid -> es desbloca
    d1.processCode("2222"); // Torna a funcionar

    System.out.println("\n=== CANVI DE COMPORTAMENT EN TEMPS D'EXECUCIÓ (CHAIN 2) ===");
    d1.setCodeHandler(chain2);
    d1.processCode("9999"); // Codi invàlid, però com que chain2 no té Lock, no es bloqueja tampoc
    d1.processCode("1111"); // Obre correctament

    System.out.println("\n=== PROVES AMB CHAIN 3 (Només Open + FireAlarm) ===");
    Door d3 = new Door("d3", chain3);
    d3.processCode("2222"); // Obre i alarma
    d3.processCode("1111"); // Obre
  }
}