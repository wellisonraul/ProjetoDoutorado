package ee.tkasekamp.ltlminer;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Properties;
import org.deckfour.xes.model.XLog;


import org.junit.Test;

public class TesteGlaucia {
	
	@Test
	public void test4() throws Exception {
		Properties props = getProps();
		
		//props.setProperty("logPath", "src/test/resources/exercisecomplete.xes");
		props.setProperty("logPath", "src/test/resources/eventlog.xes");
		
		String queries = "\" (<>(((?res1{AliquotRate:toSayAliquot})"+" /\\ (?act1{ValorAliquota}))))\"";
		//eventually_activity_A( A: activity ): <>( activity == A );
		String query01 = "\"<>(?act1{D})\"";
		
		//eventually_activity_A_and_eventually_B( A: activity, B: activity ) : (<>( activity==A) /\ <>( activity==B ));
		String query02 = "\"( (<>(?act1{A}) /\\ <>(?act2{B}) ))\""; //0.6363636363636364	(<>( activity==A ) /\ <>( activity==B ))
		String query03 = "\"( (<>(?act1{A}) -> <>(?act2{B}) ))\""; //0.8181818181818182	(<>( activity==A ) -> <>( activity==B ))
		String query04 = "\"( (<>(?act1{A}) \\/ <>(?act2{B}) ))\""; //0.9090909090909091	(<>( activity==A ) \/ <>( activity==B ))
	
		//eventually_activity_A_next_B( A: activity, B: activity ) : <>( ( activity == A /\ _O( activity == B ) ) );
		String query05 = "\"<>(( (?act1{A}) /\\ _O(?act2{B})))\""; //0.45454545454545453	<>( (activity==A /\ _O( activity==B )) )
		String query06 = "\"<>(( (?act1{A}) \\/ _O(?act2{B})))\""; //0.8181818181818182	<>( (activity==A \/ _O( activity==B )) )
		String query07 = "\"<>(( (?act1{A}) -> _O(?act2{B})))\""; //1.0	<>( (activity==A -> _O( activity==B )) )
	
		String query08 = "\"( (<>(?act1{A}) /\\ _O(?act2{B}) ))\""; //0.36363636363636365	(<>( activity==A ) /\ _O( activity==B ))
		String query09 = "\"( (<>(?act1{A}) -> _O(?act2{B}) ))\""; //0.5454545454545454	(<>( activity==A ) -> _O( activity==B ))
		String query10 = "\"( (<>(?act1{A}) \\/ _O(?act2{B}) ))\""; //0.8181818181818182	(<>( activity==A ) \/ _O( activity==B ))
	
		String query11 = "\"<>(( (?act1{A}) /\\ <>(?act2{B})))\""; //0.5454545454545454	<>( (activity==A /\ <>( activity==B )) )
		String query12 = "\"<>(( (?act1{A}) \\/ <>(?act2{B})))\""; //0.9090909090909091	<>( (activity==A \/ <>( activity==B )) )
		String query13 = "\"<>(( (?act1{A}) -> <>(?act2{B})))\""; //1.0	<>( (activity==A -> <>( activity==B )) )
	
		//COMECA AQUI
		//eventually_activity_A_and_eventually_B( A: activity, B: activity ) : (<>( activity==A) /\ <>( activity==B ));
		String query14 = "\"( ([](?act1{A}) /\\ [](?act2{B}) ))\""; //0.0	([]( activity==A ) /\ []( activity==B ))
		String query15 = "\"( ([](?act1{A}) -> [](?act2{B}) ))\""; //1.0	([]( activity==A ) -> []( activity==B ))
		String query16 = "\"( ([](?act1{A}) \\/ [](?act2{B}) ))\""; //0.0	([]( activity==A ) \/ []( activity==B ))
			
				//eventually_activity_A_next_B( A: activity, B: activity ) : <>( ( activity == A /\ _O( activity == B ) ) );
		String query17 = "\"[](( (?act1{A}) /\\ _O(?act2{B})))\""; //0.0	[]( (activity==A /\ _O( activity==B )) )

		String query18 = "\"[](( (?act1{A}) \\/ _O(?act2{B})))\""; //0.0	[]( (activity==A \/ _O( activity==B )) )
		String query19 = "\"[](( (?act1{A}) -> _O(?act2{B})))\""; //0.6363636363636364	[]( (activity==A -> _O( activity==B )) )
			
		String query20 = "\"( ([](?act1{A}) /\\ _O(?act2{B}) ))\""; //0.0	([]( activity==A ) /\ _O( activity==B ))
		String query21 = "\"( ([](?act1{A}) -> _O(?act2{B}) ))\""; //1.0	([]( activity==A ) -> _O( activity==B ))
		String query22 = "\"( ([](?act1{A}) \\/ _O(?act2{B}) ))\""; //0.36363636363636365	([]( activity==A ) \/ _O( activity==B ))
			
		String query23 = "\"[](( (?act1{A}) /\\ [](?act2{B})))\""; //0.0	[]( (activity==A /\ []( activity==B )) )
		String query24 = "\"[](( (?act1{A}) \\/ [](?act2{B})))\""; //0.0	[]( (activity==A \/ []( activity==B )) )
		String query25 = "\"[](( (?act1{A}) -> [](?act2{B})))\""; //0.18181818181818182	[]( (activity==A -> []( activity==B )) )
		
		
				
		//Teste com 3 atividades
		//As fórmulas da 26 até a 61 não funcionam por conta do parenteses que deveria ter antes de (? act2{B})
		/*String query26 = "\"( (<>(?act1{A}) /\\ <>(?act2{B}) /\\ <>(?act3{C}) ))\""; //
		String query27 = "\"( (<>(?act1{A}) -> <>(?act2{B}) -> <>(?act3{C}) ))\""; //
		String query28 = "\"( (<>(?act1{A}) \\/ <>(?act2{B}) \\/ <>(?act3{C}) ))\""; //
			
		String query29 = "\"<>(( (?act1{A}) /\\ <>(?act2{B}) /\\ <>(?act3{C})))\""; //
		String query30 = "\"<>(( (?act1{A}) \\/ <>(?act2{B}) \\/ <>(?act3{C})))\""; //
		String query31 = "\"<>(( (?act1{A}) -> <>(?act2{B}) -> <>(?act3{C})))\""; //
		
		String query32 = "\"( (<>(?act1{A}) /\\ _O(?act2{B}) /\\ _O(?act3{C}) ))\""; //
		String query33 = "\"( (<>(?act1{A}) -> _O(?act2{B}) -> _O(?act3{C}) ))\""; //
		String query34 = "\"( (<>(?act1{A}) \\/ _O(?act2{B}) \\/ _O(?act3{C}) ))\""; //
	
		String query35 = "\"<>(( (?act1{A}) /\\ _O(?act2{B}) /\\ _O(?act3{C})))\""; //
		String query36 = "\"<>(( (?act1{A}) \\/ _O(?act2{B}) \\/ _O(?act3{C})))\""; //
		String query37 = "\"<>(( (?act1{A}) -> _O(?act2{B}) -> _O(?act3{C})))\""; //
	
		String query38 = "\"( (<>(?act1{A}) /\\ [](?act2{B}) /\\ [](?act3{C}) ))\""; //
		String query39 = "\"( (<>(?act1{A}) -> [](?act2{B}) -> [](?act3{C}) ))\""; //
		String query40 = "\"( (<>(?act1{A}) \\/ [](?act2{B}) \\/ [](?act3{C}) ))\""; //
	
		String query41 = "\"<>(( (?act1{A}) /\\ [](?act2{B}) /\\ [](?act3{C})))\""; //
		String query42 = "\"<>(( (?act1{A}) \\/ [](?act2{B}) \\/ [](?act3{C})))\""; //
		String query43 = "\"<>(( (?act1{A}) -> [](?act2{B}) -> [](?act3{C})))\""; //
	
		//---------
		
		String query44 = "\"( ([](?act1{A}) /\\ [](?act2{B}) /\\ [](?act3{C}) ))\""; //
		String query45 = "\"( ([](?act1{A}) -> [](?act2{B}) -> [](?act3{C}) ))\""; //
		String query46 = "\"( ([](?act1{A}) \\/ [](?act2{B}) \\/ [](?act3{C}) ))\""; //

		String query47 = "\"[](( (?act1{A}) /\\ [](?act2{B}) /\\ [](?act3{C})))\""; //
		String query48 = "\"[](( (?act1{A}) \\/ [](?act2{B}) \\/ [](?act3{C})))\""; //
		String query49 = "\"[](( (?act1{A}) -> [](?act2{B}) -> [](?act3{C})))\""; //

		String query50 = "\"[](( (?act1{A}) /\\ _O(?act2{B}) /\\ _O(?act3{C})))\""; //
        String query51 = "\"[](( (?act1{A}) \\/ _O(?act2{B}) \\/ _O(?act3{C})))\""; //
		String query52 = "\"[](( (?act1{A}) -> _O(?act2{B}) -> _O(?act3{C})))\""; //
					
		String query53 = "\"( ([](?act1{A}) /\\ _O(?act2{B}) /\\ _O(?act3{C}) ))\""; //
		String query54 = "\"( ([](?act1{A}) -> _O(?act2{B}) -> _O(?act3{C}) ))\""; //
		String query55 = "\"( ([](?act1{A}) \\/ _O(?act2{B}) \\/ _O(?act3{C}) ))\""; //
					
		String query56 = "\"( ([](?act1{A}) /\\ <>(?act2{B}) /\\ <>(?act3{C}) ))\""; //
		String query57 = "\"( ([](?act1{A}) -> <>(?act2{B}) -> <>(?act3{C}) ))\""; //
		String query58 = "\"( ([](?act1{A}) \\/ <>(?act2{B}) \\/ <>(?act3{C}) ))\""; //
	
		String query59 = "\"[](( (?act1{A}) /\\ <>(?act2{B}) /\\ <>(?act3{C})))\""; //
		String query60 = "\"[](( (?act1{A}) \\/ <>(?act2{B}) \\/ <>(?act3{C})))\""; //
		String query61 = "\"[](( (?act1{A}) -> <>(?act2{B}) -> <>(?act3{C})))\""; //
		*/
		//-----------------
			
		
		String query62 = "\"<>(( (?act1{A}) /\\ <>( ( ?act2{B} /\\ <>( ?act3{C} ) ) ) ) )\""; //
		String query63 = "\"<>(( (?act1{A}) -> <>( ( ?act2{B} -> <>( ?act3{C} ) ) ) ) )\""; //
		String query64 = "\"<>(( (?act1{A}) \\/ <>( ( ?act2{B} \\/ <>( ?act3{C} ) ) ) ) )\"";//		
		
		String query65 = "\"(( <>(?act1{A}) /\\ <>( ( ?act2{B} /\\ <>( ?act3{C} ) ) ) ) )\""; //
		String query66 = "\"(( <>(?act1{A}) -> <>( ( ?act2{B} -> <>( ?act3{C} ) ) ) ) )\""; //
		String query67 = "\"(( <>(?act1{A}) \\/ <>( ( ?act2{B} \\/ <>( ?act3{C} ) ) ) ) )\"";//
		
		String query68 = "\"<>(( (?act1{A}) /\\ _O( ( ?act2{B} /\\ _O( ?act3{C} ) ) ) ) )\""; //
		String query69 = "\"<>(( (?act1{A}) -> _O( ( ?act2{B} -> _O( ?act3{C} ) ) ) ) )\""; //
		String query70 = "\"<>(( (?act1{A}) \\/ _O( ( ?act2{B} \\/ _O( ?act3{C} ) ) ) ) )\"";//		
		
		String query71 = "\"(( <>(?act1{A}) /\\ _O( ( ?act2{B} /\\ _O( ?act3{C} ) ) ) ) )\""; //
		String query72 = "\"(( <>(?act1{A}) -> _O( ( ?act2{B} -> _O( ?act3{C} ) ) ) ) )\""; //
		String query73 = "\"(( <>(?act1{A}) \\/ _O( ( ?act2{B} \\/ _O( ?act3{C} ) ) ) ) )\"";//
		
		String query74 = "\"<>(( (?act1{A}) /\\ []( ( ?act2{B} /\\ []( ?act3{C} ) ) ) ) )\""; //
		String query75 = "\"<>(( (?act1{A}) -> []( ( ?act2{B} -> []( ?act3{C} ) ) ) ) )\""; //
		String query76 = "\"<>(( (?act1{A}) \\/ []( ( ?act2{B} \\/ []( ?act3{C} ) ) ) ) )\"";//		
		
		String query77 = "\"(( <>(?act1{A}) /\\ []( ( ?act2{B} /\\ []( ?act3{C} ) ) ) ) )\""; //
		String query78 = "\"(( <>(?act1{A}) -> []( ( ?act2{B} -> []( ?act3{C} ) ) ) ) )\""; //
		String query79 = "\"(( <>(?act1{A}) \\/ []( ( ?act2{B} \\/ []( ?act3{C} ) ) ) ) )\"";//
		
		//-------
		
		String query80 = "\"[](( (?act1{A}) /\\ <>( ( ?act2{B} /\\ <>( ?act3{C} ) ) ) ) )\""; //
		String query81 = "\"[](( (?act1{A}) -> <>( ( ?act2{B} -> <>( ?act3{C} ) ) ) ) )\""; //
		String query82 = "\"[](( (?act1{A}) \\/ <>( ( ?act2{B} \\/ <>( ?act3{C} ) ) ) ) )\"";//		
		
		String query83 = "\"(( [](?act1{A}) /\\ <>( ( ?act2{B} /\\ <>( ?act3{C} ) ) ) ) )\""; //
		String query84 = "\"(( [](?act1{A}) -> <>( ( ?act2{B} -> <>( ?act3{C} ) ) ) ) )\""; //
		String query85 = "\"(( [](?act1{A}) \\/ <>( ( ?act2{B} \\/ <>( ?act3{C} ) ) ) ) )\"";//
		
		
		String query86 = "\"[](( (?act1{A}) /\\ _O( ( ?act2{B} /\\ _O( ?act3{C} ) ) ) ) )\""; //
		String query87 = "\"[](( (?act1{A}) -> _O( ( ?act2{B} -> _O( ?act3{C} ) ) ) ) )\""; //
		String query88 = "\"[](( (?act1{A}) \\/ _O( ( ?act2{B} \\/ _O( ?act3{C} ) ) ) ) )\"";//		
		
		String query89 = "\"(( [](?act1{A}) /\\ _O( ( ?act2{B} /\\ _O( ?act3{C} ) ) ) ) )\""; //
		String query90 = "\"(( [](?act1{A}) -> _O( ( ?act2{B} -> _O( ?act3{C} ) ) ) ) )\""; //
		String query91 = "\"(( [](?act1{A}) \\/ _O( ( ?act2{B} \\/ _O( ?act3{C} ) ) ) ) )\"";//
		
		String query92 = "\"[](( (?act1{A}) /\\ []( ( ?act2{B} /\\ []( ?act3{C} ) ) ) ) )\""; //
		String query93 = "\"[](( (?act1{A}) -> []( ( ?act2{B} -> []( ?act3{C} ) ) ) ) )\""; //
		String query94 = "\"[](( (?act1{A}) \\/ []( ( ?act2{B} \\/ []( ?act3{C} ) ) ) ) )\"";//		
		
		String query95 = "\"(( [](?act1{A}) /\\ []( ( ?act2{B} /\\ []( ?act3{C} ) ) ) ) )\""; //
		String query96 = "\"(( [](?act1{A}) -> []( ( ?act2{B} -> []( ?act3{C} ) ) ) ) )\""; //
		String query97 = "\"(( [](?act1{A}) \\/ []( ( ?act2{B} \\/ []( ?act3{C} ) ) ) ) )\"";//
		
		
		String query98 = "\" (<>(?act1{A}) /\\ <> (( ?act2{B} /\\ <>( ?act3{C} ) ) ))\"";
		
		
		String query99 = "\"(?act1{A})\""; //fórmula do init
		String query100 = "\"<>(?act1{A})\"";
		String query101 = "\"!( <>(((?act1{A}) /\\ _O(?act1{A}))) )\"";
		String query102 = "\"(<>(?act1{A}) <-> <>(?act2{B}))\"";
		String query103 = "\"(<>(?act1{A}) -> <>(?act2{B}))\"";
		String query104 = "\"[]( (?act1{A} -> <>(?act2{B})) )\"";
		String query105 = "\"(<>(?act1{A}) -> <>(?act2{B}))\"";
		String query106 = "\"[]( (?act1{A} -> <>(?act2{B})) )\"";
		String query107 = "\"( (!(?act2{B}) _U ?act1{A}) \\/ [](!(?act2{B})) ) \"";
			
		String query108 = "\" [](( (?act1{A} -> <>(?act2{B})) /\\ ( (!(?act2{B}) _U ?act1{A}) \\/ [](!(?act2{B})) ) )) \"";
		String query109 = "\"[]( (?act1{A} -> _O( (!(?act1{A}) _U ?act2{B}) ) ) )\"";
		String query110 = "\"(( (!(?act2{B}) _U ?act1{A}) \\/ [] (!(?act2{B})) ) /\\ []( (?act2{B} -> _O(( (!(?act2{B}) _U ?act1{A}) \\/ [] (!(?act2{B})) )))))\"";
		String query111 = "\"([]( (?act1{A} -> _O( (!(?act1{A}) _U ?act2{B}) ) ) ) /\\ (( (!(?act2{B}) _U ?act1{A}) \\/ [] (!(?act2{B})) ) /\\ []( (?act2{B} -> _O(( (!(?act2{B}) _U ?act1{A}) \\/ [](!(?act2{B})) ))))))\"";
		String query112 = "\"[]( (?act1{A} -> _O(?act2{B}) ))\"";
		String query113 = "\"[]( (_O(?act2{B}) -> ?act1{A} ))\"";
		String query114 = "\"[]( (?act1{A} <-> _O(?act2{B}) ))\"";
		String query115 = "\"!( (<>(?act1{A}) /\\ <>(?act2{B}) ))\"";
		String query116 = "\"[]( (?act1{A} -> !(<> (?act2{B}) )))\"";
		String query117 = "\"[]( (?act1{A} -> _O(!(?act2{B})) ))\"";
		
				
		
				
		
		
				
				
				//eventually_activity_A_and_eventually_B_and_eventually_C ( A: activity, B: activity, C: activity ) :<>( (activity==A  /\ <>(( activity==B /\ <>(activity==C )))));
		//String query30 = "\"<>(( (?act1{A}) /\\ <>( ( ?act2{B} /\\ <>( ?act3{C} ) ) ) ) )\"";
		//String query31 = "\"<>(( (?act1{A}) -> <>( ( ?act2{B} -> <>( ?act3{C} ) ) ) ) )\"";
		
		String testeWellison = "\"[](( (?act1{A})  ->  <>(?act2{B})))\"; \"<>(?act3{C})\"; \"<>(?act4{A})\"";
		
		
		String testeGlaucia = "\"<>(?act5{C})\";\" (<>(?act1{A}) /\\ <>( ?act2{B}))\"; \"<>(?act4{D})\"";
		
		
		String queriesEventype= "\" (?act1{ValorAliquota})"+" /\\ (?typ{complete}) \"";
		
		
		
		
		
		props.setProperty("queries", queriesEventype);
		
		
		LTLMinerStarter starter = new LTLMinerStarter(props);
	
		starter.mine();
	}
	
	private Properties getProps() {
		Properties props = new Properties();
		//props.setProperty("logPath", "src/test/resources/exercise1.xes");
		props.setProperty("considerEventTypes", "true");
		props.setProperty("minSupport", "0.0");
		props.setProperty("outputFormat", "console");
		//String queries = "\"[](( (?x)  ->  <>(?y)))\"; \"<>(?x)\"";
		//props.setProperty("queries", queries);

		return props;
	}

}