import java.util.Scanner;

public class Game_Play {
    String ANSI_RESET = "\u001B[0m";
    String ANSI_GREEN = "\u001B[32m";

    Scanner scan = new Scanner(System.in);
    지연 지연_1000 = new 지연(1000);
    //승리소리 //승리소리 = new //승리소리();
    //배경음 //배경음 = new //배경음();

    int choice;
    int choice_game_rounds;
    int playnumber;
    static int die_count = 0;
    static boolean die = false;
    boolean bankruptcy = false;
    boolean die1 = false;
    boolean die2 = false;
    boolean die3 = false;
    boolean die4 = false;
    boolean round = false;

    void game_play(){
        //배경음.start();
        System.out.println("     _______. _______   ______    __    __   __         .___  ___.      ___      .______      .______    __       _______ \n" +
                "    /       ||   ____| /  __  \\  |  |  |  | |  |        |   \\/   |     /   \\     |   _  \\     |   _  \\  |  |     |   ____|\n" +
                "   |   (----`|  |__   |  |  |  | |  |  |  | |  |        |  \\  /  |    /  ^  \\    |  |_)  |    |  |_)  | |  |     |  |__   \n" +
                "    \\   \\    |   __|  |  |  |  | |  |  |  | |  |        |  |\\/|  |   /  /_\\  \\   |      /     |   _  <  |  |     |   __|  \n" +
                ".----)   |   |  |____ |  `--'  | |  `--'  | |  `----.   |  |  |  |  /  _____  \\  |  |\\  \\----.|  |_)  | |  `----.|  |____ \n" +
                "|_______/    |_______| \\______/   \\______/  |_______|   |__|  |__| /__/     \\__\\ | _| `._____||______/  |_______||_______|\n" +
                "                                                                                                                          ");
        
        System.out.println();
        지연_1000.delay();
        System.out.println("//////////////////////////////////////서울마불 (SEOUL MARBLE)////////////////////////////////////////////////////");
        System.out.println("부루마불을 참고해서 만든 게임으로 외지인이 서울 지역 안에서 급여를 받으며 조건 속에서 살아남아 돈을 가장 많이 버는 플레이어가 승리하는 게임");
        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println();
        지연_1000.delay();
        System.out.println("///////////////////////////////////////////////게임 방법/////////////////////////////////////////////////////////");
        System.out.println("서울 지역을 한바퀴 돌도록 게임이 설계 되어있고 지역마다 할 수 있는 활동을 결정 해서 캐릭터 능력을 키울 수 있다");
        System.out.println("주사위(1~12까지)를 던지게 되면 총 25개 지역 안에서 플레이어가 주사위 숫자만큼 이동하게 되고 도착 한 지역에서 할 활동을 결정 한다");
        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println();

        지연_1000.delay();
        System.out.println("도움말을 보시겠습니까?");
        System.out.println("1.예 2.아니요");
        choice = scan.nextInt();
        while (choice != 1 && choice != 2) {
            System.out.println("잘못된 값입니다");
            System.out.println("다시 선택해 주세요");
            choice = scan.nextInt();
        }
        if(choice == 1){
            System.out.println("체   력  : 게이지가 다 닳으면 게임오버\n" +
                               "스트레스  : 게이지가 가득 차면 체력이 10씩 깎인다\n" +
                               "운동 능력 : 게이지가 가득 차면 체력이 10씩 올라간다\n" +
                               "현    금 : 지역을 한바퀴 돌면 월급코인을 지급 받는다\n" +
                               "           (파산을 하게 되면 게임 오버)\n" +
                               "부 동 산  : 매매 – 매매계약금 외 지출 없음, 한바퀴당 부동산에 해당하는 월세를 받을 수 있음\n" +
                               "           전세 – 전세계약금 외 지출, 수입 없음\n" +
                               "           월세 – 한바퀴 돌 때 마다 월세 지출 있음\n" +
                               "           (아무런 부동산 계약을 않으면 한바퀴 돌 때 마다 20coin의 월세를 내게 됨)\n" +
                               "지     식 : 게이지가 가득 차면 월급코인이 100coin씩 올라간다\n" +
                               "\n" +
                               "지역이 이동 될 때 마다 스트레스가 10씩 증가한다\n" +
                               "부동산 계약은 현재 위치에서만 가능하다\n"+
                               "지역을 모두 돌아서 출발 지점을 지나거나 도착하게 되면 코인을 지급 받게 된다\n");
            System.out.println("게임을 시작 하려면 0을 입력 하세요");
        }
        else if(choice == 2){
            System.out.println("게임을 시작 하려면 0을 입력 하세요");
        }
        choice = scan.nextInt();
        while (choice != 0) {
            System.out.println("잘못된 값입니다");
            System.out.println("게임을 시작 하려면 0을 입력 하세요");
            choice = scan.nextInt();
        }
        System.out.println("게임을 진행 할 인원수를 선택 하세요");
        System.out.println("1.'2인' 2.'3인' 3.'4인'");
        choice = scan.nextInt();
        while (choice != 1 && choice != 2 && choice != 3) {
            System.out.println("잘못 선택 했습니다");
            System.out.println("게임을 진행 할 인원수를 다시 선택 하세요");
            choice = scan.nextInt();
        }
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();

        playnumber = choice;
        if (playnumber == 1) {
            System.out.println();
            System.out.println("//////////////////////////////////////2인용 게임으로 선택 했습니다./////////////////////////////////////////////////");
            System.out.println();
            player1.make_player("1P");
            player1.map_print();
            player2.make_player("2P");
            player2.map_print();
        } else if (playnumber == 2) {
            System.out.println();
            System.out.println("//////////////////////////////////////3인용 게임으로 선택 했습니다./////////////////////////////////////////////////");
            System.out.println();
            player1.make_player("1P");
            player1.map_print();
            player2.make_player("2P");
            player2.map_print();
            player3.make_player("3P");
            player3.map_print();
        } else if (playnumber == 3) {
            System.out.println();
            System.out.println("//////////////////////////////////////4인용 게임으로 선택 했습니다./////////////////////////////////////////////////");
            System.out.println();
            player1.make_player("1P");
            player1.map_print();
            player2.make_player("2P");
            player2.map_print();
            player3.make_player("3P");
            player3.map_print();
            player4.make_player("4P");
            player4.map_print();
        }

        Dice dice = new Dice();
        Special_Dice special_dice = new Special_Dice();

        System.out.println("지역을 몇바퀴 돌면 게임을 종료 하시겠습니까? 원하는 바퀴수를 입력 하세요");
        choice_game_rounds = scan.nextInt();
        while (choice_game_rounds <= 0) {
            System.out.println("잘못 입력 했습니다");
            System.out.println("원하는 바퀴수를 양수로 입력 하세요");
            choice_game_rounds = scan.nextInt();
        }
        System.out.println(choice_game_rounds+"바퀴 후 현금을 가장 많이 가진 플레이어가 승리하게 됩니다");

        System.out.println();
        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("/////////////////////////////////////////////게임 시작!!!////////////////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println();
        지연_1000.delay();
        int lap = 0;
        int lap_calculation1 = 0;
        int lap_calculation2 = 0;
        int lap_calculation3 = 0;
        int lap_calculation4 = 0;

        while (true) {
            round = false;
            /*
            게임 종료 조건
             */
            if(playnumber == 1 && lap_calculation1 == choice_game_rounds && lap_calculation2 == choice_game_rounds) {
                lap++;
                if(lap == choice_game_rounds) {
                    if (player1.coin == player2.coin && player2.coin == player3.coin) {
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println("무승부 입니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                    if (player1.coin > player2.coin) {
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println(player1.name + "님이 승리 했습니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                    if (player2.coin > player1.coin) {
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println(player2.name + "님이 승리 했습니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                }
            }
            if(playnumber == 2 && lap_calculation1 == choice_game_rounds && lap_calculation2 == choice_game_rounds && lap_calculation3 == choice_game_rounds) {
                lap++;
                if(lap == choice_game_rounds) {
                    if (player1.coin == player2.coin && player2.coin == player3.coin) {
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println("무승부 입니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                    if (player1.coin > player2.coin && player1.coin > player3.coin) {
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println(player1.name + "님이 승리 했습니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                    if (player2.coin > player1.coin && player2.coin > player3.coin) {
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println(player2.name + "님이 승리 했습니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                    if (player3.coin > player1.coin && player3.coin > player2.coin) {
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println(player3.name + "님이 승리 했습니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                }
            }
            if(playnumber == 3 && lap_calculation1 == choice_game_rounds && lap_calculation2 == choice_game_rounds && lap_calculation3 == choice_game_rounds && lap_calculation4 == choice_game_rounds) {
                lap++;
                if (player1.coin == player2.coin && player2.coin == player3.coin && player3.coin == player4.coin) {
                    //배경음.setStop(true);
                    지연_1000.delay();
                    System.out.println();
                    System.out.println("무승부 입니다");
                    System.out.println("게임 종료");
                    //승리소리.start();
                    지연_1000.delay();
                    System.exit(0);
                }
                if (player1.coin > player2.coin && player1.coin > player3.coin && player1.coin > player4.coin) {
                    //배경음.setStop(true);
                    지연_1000.delay();
                    System.out.println();
                    System.out.println(player1.name + "님이 승리 했습니다");
                    System.out.println("게임 종료");
                    //승리소리.start();
                    지연_1000.delay();
                    System.exit(0);
                }
                if (player2.coin > player1.coin && player2.coin > player3.coin && player2.coin > player4.coin) {
                    //배경음.setStop(true);
                    지연_1000.delay();
                    System.out.println();
                    System.out.println(player2.name + "님이 승리 했습니다");
                    System.out.println("게임 종료");
                    //승리소리.start();
                    지연_1000.delay();
                    System.exit(0);
                }
                if (player3.coin > player1.coin && player3.coin > player2.coin && player3.coin > player4.coin) {
                    //배경음.setStop(true);
                    지연_1000.delay();
                    System.out.println();
                    System.out.println(player3.name + "님이 승리 했습니다");
                    System.out.println("게임 종료");
                    //승리소리.start();
                    지연_1000.delay();
                    System.exit(0);
                }
                if (player4.coin > player1.coin && player4.coin > player2.coin && player4.coin > player3.coin) {
                    //배경음.setStop(true);
                    지연_1000.delay();
                    System.out.println();
                    System.out.println(player4.name + "님이 승리 했습니다");
                    System.out.println("게임 종료");
                    //승리소리.start();
                    지연_1000.delay();
                    System.exit(0);
                }
            }
            /*
                게임 진행
                 */
            for (int b = 0; b < playnumber + 1; b++) {
                /*
                palyer1 게임 진행
                 */
                if (b == 0 && die1 == false && lap_calculation1 < choice_game_rounds && bankruptcy == false) {
                    if(playnumber == 1 && die_count == 1){
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println(player1.name+"님이 승리 했습니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                    if(playnumber == 2 && die_count == 2){
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println(player1.name+"님이 승리 했습니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                    if(playnumber == 3 && die_count == 3){
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println(player1.name+"님이 승리 했습니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                    System.out.println("////////////////////////////////////////");
                    System.out.println(ANSI_GREEN+"1P : "+player1.name+ANSI_RESET);
                    System.out.println("////////////////////////////////////////");
                    player1.Print_Ability_Gauge();
                    player1.map_print();

                    System.out.println(lap_calculation1+"/"+choice_game_rounds);
                    System.out.println();
                    player1.current_area += dice.dice_throw();
                    System.out.println("주사위 값은 " + dice.random + "입니다. " + dice.random + "칸 이동 하세요.");
                    지연_1000.delay();

                    player1.stress += 10;
                    System.out.println();
                    System.out.println("지역 이동으로인해 스트레스가 " + 10 + "증가 했습니다");
                    지연_1000.delay();
                    if(player1.stress > 10){
                        player1.health += player1.damage_10;
                        player1.stress = player1.stress-10;
                        System.out.println("================================================================================================================");
                        System.out.println("스트레스를 많이 받아서 체력이 -10 떨어졌습니다");
                        System.out.println("================================================================================================================");
                        지연_1000.delay();
                    }
                    if(player1.current_area > 25){
                        player1.current_area = player1.current_area - 26;
                        round = true;
                    }
                    if(round == true){
                        lap_calculation1++;
                        if(lap_calculation1 == choice_game_rounds){
                            System.out.println(choice_game_rounds+"바퀴를 다 돌았다");
                            지연_1000.delay();
                            break;
                        }
                        player1.salary_calculation();
                        player1.monthly_supply(player1.real_estate);

                    }

                    if(player1.coin < 0){
                        System.out.println("파산 했습니다");
                        bankruptcy = true;
                        break;
                    }

                    if(player1.current_area == 0 && round == true){
                        System.out.println();
                        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                        System.out.println("////////////////////////////////////////////////보너스 코인!!!!//////////////////////////////////////////////////");
                        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                        System.out.println();
                        지연_1000.delay();
                        player1.coin += special_dice.special_dice_throw();
                        System.out.println(special_dice.random+" Coin의 보너스 코인을 획득 했습니다!!!");
                        지연_1000.delay();
                        break;
                    }
                    player1.map_print();
                    player1.activity_calculation(player1.activity_choice());
                    while(true){
                        if(player1.activity_choice.equals("도움말")){
                            player1.activity_calculation(player1.activity_choice());
                        }
                        else{
                            break;
                        }
                    }
                    if(die == true){
                        die1 = true;
                        die = false;
                        break;
                    }
                    player1.real_estate_contract(player1.current_area);
                    player1.Print_Ability_Gauge();
                }
                /*
                palyer2 게임 진행
                 */
                if (b == 1 && die2 == false && lap_calculation2 < choice_game_rounds && bankruptcy == false) {
                    if(playnumber == 1 && die_count == 1){
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println(player2.name+"님이 승리 했습니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                    if(playnumber == 2 && die_count == 2){
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println(player2.name+"님이 승리 했습니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                    if(playnumber == 3 && die_count == 3){
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println(player2.name+"님이 승리 했습니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                    System.out.println("////////////////////////////////////////");
                    System.out.println(ANSI_GREEN+"2P : "+player2.name+ANSI_RESET);
                    System.out.println("////////////////////////////////////////");
                    player2.Print_Ability_Gauge();
                    player2.map_print();

                    System.out.println(lap_calculation2+"/"+choice_game_rounds);
                    System.out.println();
                    player2.current_area += dice.dice_throw();
                    System.out.println("주사위 값은 " + dice.random + "입니다. " + dice.random + "칸 이동 하세요.");
                    지연_1000.delay();

                    player2.stress += 10;
                    System.out.println();
                    System.out.println("지역 이동으로인해 스트레스가 " + 10 + "증가 했습니다");
                    지연_1000.delay();
                    if(player2.stress > 10){
                        player2.health += player2.damage_10;
                        player2.stress = player2.stress-10;
                        System.out.println("================================================================================================================");
                        System.out.println("스트레스를 많이 받아서 체력이 -10 떨어졌습니다");
                        System.out.println("================================================================================================================");
                        지연_1000.delay();
                    }
                    if(player2.current_area > 25){
                        player2.current_area = player2.current_area - 26;
                        round = true;
                    }
                    if(round == true){
                        lap_calculation2++;
                        if(lap_calculation2 == choice_game_rounds){
                            System.out.println(choice_game_rounds+"바퀴를 다 돌았다");
                            지연_1000.delay();
                            break;
                        }
                        player2.salary_calculation();
                        player2.monthly_supply(player2.real_estate);

                    }
                    if(player2.coin < 0){
                        System.out.println("파산 했습니다");
                        bankruptcy = true;
                        break;
                    }
                    if(player2.current_area == 0 && round == true){
                        System.out.println();
                        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                        System.out.println("////////////////////////////////////////////////보너스 코인!!!!//////////////////////////////////////////////////");
                        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                        System.out.println();
                        지연_1000.delay();
                        player2.coin += special_dice.special_dice_throw();
                        System.out.println(special_dice.random+"Coin의 보너스 코인을 획득 했습니다!!!");
                        지연_1000.delay();
                        break;
                    }
                    player2.map_print();
                    player2.activity_calculation(player2.activity_choice());
                    while(true){
                        if(player2.activity_choice.equals("도움말")){
                            player2.activity_calculation(player2.activity_choice());
                        }
                        else{
                            break;
                        }
                    }
                    if(die == true){
                        die2 = true;
                        die = false;
                        break;
                    }
                    player2.real_estate_contract(player2.current_area);
                    player2.Print_Ability_Gauge();
                }
                /*
                palyer3 게임 진행
                 */
                if (b == 2 && die3 == false && lap_calculation3 < choice_game_rounds && bankruptcy == false) {
                    if(playnumber == 1 && die_count == 1){
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println(player3.name+"님이 승리 했습니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                    if(playnumber == 2 && die_count == 2){
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println(player3.name+"님이 승리 했습니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                    if(playnumber == 3 && die_count == 3){
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println(player3.name+"님이 승리 했습니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                    System.out.println("////////////////////////////////////////");
                    System.out.println(ANSI_GREEN+"3P : "+player3.name+ANSI_RESET);
                    System.out.println("////////////////////////////////////////");
                    player3.Print_Ability_Gauge();
                    player3.map_print();

                    System.out.println(lap_calculation3+"/"+choice_game_rounds);
                    System.out.println();
                    player3.current_area += dice.dice_throw();
                    System.out.println("주사위 값은 " + dice.random + "입니다. " + dice.random + "칸 이동 하세요.");
                    지연_1000.delay();

                    player3.stress += 10;
                    System.out.println();
                    System.out.println("지역 이동으로인해 스트레스가 " + 10 + "증가 했습니다");
                    지연_1000.delay();
                    if(player3.stress > 10){
                        player3.health += player3.damage_10;
                        player3.stress = player3.stress-10;
                        System.out.println("================================================================================================================");
                        System.out.println("스트레스를 많이 받아서 체력이 -10 떨어졌습니다");
                        System.out.println("================================================================================================================");
                        지연_1000.delay();
                    }
                    if(player3.current_area > 25){
                        player3.current_area = player3.current_area - 26;
                        round = true;
                    }
                    if(round == true){
                        lap_calculation3++;
                        if(lap_calculation3 == choice_game_rounds){
                            System.out.println(choice_game_rounds+"바퀴를 다 돌았다");
                            지연_1000.delay();
                            break;
                        }
                        player3.salary_calculation();
                        player3.monthly_supply(player3.real_estate);

                    }
                    if(player3.coin < 0){
                        System.out.println("파산 했습니다");
                        bankruptcy = true;
                        break;
                    }
                    if(player3.current_area == 0 && round == true){
                        System.out.println();
                        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                        System.out.println("////////////////////////////////////////////////보너스 코인!!!!//////////////////////////////////////////////////");
                        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                        System.out.println();
                        지연_1000.delay();
                        player3.coin += special_dice.special_dice_throw();
                        System.out.println(special_dice.random+"Coin의 보너스 코인을 획득 했습니다!!!");
                        지연_1000.delay();
                        break;
                    }
                    player3.map_print();
                    player3.activity_calculation(player3.activity_choice());
                    while(true){
                        if(player3.activity_choice.equals("도움말")){
                            player3.activity_calculation(player3.activity_choice());
                        }
                        else{
                            break;
                        }
                    }
                    if(die == true){
                        die3 = true;
                        die = false;
                        break;
                    }
                    player3.real_estate_contract(player3.current_area);
                    player3.Print_Ability_Gauge();
                }
                /*
                palyer4 게임 진행
                 */
                if (b == 3 && die4 == false && lap_calculation4 < choice_game_rounds && bankruptcy == false) {
                    if(playnumber == 1 && die_count == 1){
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println(player4.name+"님이 승리 했습니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                    if(playnumber == 2 && die_count == 2){
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println(player4.name+"님이 승리 했습니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                    if(playnumber == 3 && die_count == 3){
                        //배경음.setStop(true);
                        지연_1000.delay();
                        System.out.println();
                        System.out.println(player4.name+"님이 승리 했습니다");
                        System.out.println("게임 종료");
                        //승리소리.start();
                        지연_1000.delay();
                        System.exit(0);
                    }
                    System.out.println("////////////////////////////////////////");
                    System.out.println(ANSI_GREEN+"4P : "+player4.name+ANSI_RESET);
                    System.out.println("////////////////////////////////////////");
                    player4.Print_Ability_Gauge();
                    player4.map_print();

                    System.out.println(lap_calculation4+"/"+choice_game_rounds);
                    System.out.println();
                    player4.current_area += dice.dice_throw();
                    System.out.println("주사위 값은 " + dice.random + "입니다. " + dice.random + "칸 이동 하세요.");
                    지연_1000.delay();

                    player4.stress += 10;
                    System.out.println();
                    System.out.println("지역 이동으로인해 스트레스가 " + 10 + "증가 했습니다");
                    지연_1000.delay();
                    if(player4.stress > 10){
                        player4.health += player4.damage_10;
                        player4.stress = player4.stress-10;
                        System.out.println("================================================================================================================");
                        System.out.println("스트레스를 많이 받아서 체력이 -10 떨어졌습니다");
                        System.out.println("================================================================================================================");
                        지연_1000.delay();
                    }
                    if(player4.current_area > 25){
                        player4.current_area = player4.current_area - 26;
                        round = true;
                    }
                    if(round == true){
                        lap_calculation4++;
                        if(lap_calculation4 == choice_game_rounds){
                            System.out.println(choice_game_rounds+"바퀴를 다 돌았다");
                            지연_1000.delay();
                            break;
                        }
                        player4.salary_calculation();
                        player4.monthly_supply(player4.real_estate);

                    }
                    if(player4.coin < 0){
                        System.out.println("파산 했습니다");
                        bankruptcy = true;
                        break;
                    }
                    if(player4.current_area == 0 && round == true){
                        System.out.println();
                        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                        System.out.println("////////////////////////////////////////////////보너스 코인!!!!//////////////////////////////////////////////////");
                        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                        System.out.println();
                        지연_1000.delay();
                        player4.coin += special_dice.special_dice_throw();
                        System.out.println(special_dice.random+"Coin의 보너스 코인을 획득 했습니다!!!");
                        지연_1000.delay();
                        break;
                    }
                    player4.map_print();
                    player4.activity_calculation(player4.activity_choice());
                    while(true){
                        if(player4.activity_choice.equals("도움말")){
                            player4.activity_calculation(player4.activity_choice());
                        }
                        else{
                            break;
                        }
                    }
                    if(die == true){
                        die4 = true;
                        die = false;
                        break;
                    }
                    player4.real_estate_contract(player4.current_area);
                    player4.Print_Ability_Gauge();
                }
            }
        }
    }
}
