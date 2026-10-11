package com.waltenfiles.lore;

/**
 * Texto das fitas cassete. ATENÇÃO: são textos-base originais e genéricos.
 * Troque pelos textos da lore oficial de The Walten Files como preferir.
 */
public final class TapeLore {
    public static final int COUNT = 8;

    private static final String[] TITLES = {
            "Fita 1 - A Casa dos Walten",
            "Fita 2 - Depois do Fechamento",
            "Fita 3 - Os Walten",
            "Fita 4 - Projeto K9",
            "Fita 5 - Realocação",
            "Fita 6 - O Homem das Sombras",
            "Fita 7 - Saint Juana",
            "Fita 8 - Edd e Molly"
    };

    private static final String[][] PAGES = {
            {
                    "[CHIADO DE ESTÁTICA] Imagem de um salão iluminado. Música alta, risadas de criança. Ao fundo, no palco, quatro silhuetas imóveis: Bon, Sha, Boozoo e Banny. Uma voz fora de quadro diz: 'Sorriam. A câmera está ligada.'",
                    "A família Walten acena para a lente. Ninguém pisca. A fita corta antes do fim da música, mas o som continua por alguns segundos, devagar, como se alguém ainda estivesse cantando."
            },
            {
                    "Madrugada. O salão está vazio, mas a câmera de segurança registra movimento no palco. Um a um, os animatrônicos viram a cabeça. Nunca todos ao mesmo tempo.",
                    "Alguém deixou um bilhete preso na câmera: 'Eles só despertam à noite. Alguns param quando você os encara. Se você desviar o olhar, eles se aproximam.'"
            },
            {
                    "Jack, Rosemary, Sophie, Edd e Molly. Cinco rostos na mesa do jantar, em todas as gravações antigas. Nas mais recentes, as cadeiras continuam ocupadas, mas os rostos estão errados.",
                    "Jack não responde ao próprio nome. Rosemary repete a mesma frase em todas as fitas. Sophie aparece sempre atrás da câmera. Edd e Molly deixaram de aparecer, e ninguém sabe dizer quando."
            },
            {
                    "Gravação de áudio. Uma voz burocrática lê um memorando: 'O projeto de realocação K9 foi aprovado. Os participantes serão encaminhados em silêncio. Não haverá registro oficial.'",
                    "O chiado aumenta. Ao fundo, portas de metal fechando em sequência. A voz continua: 'Quem chegar ao K9 não precisa se preocupar com o caminho de volta.'"
            },
            {
                    "Imagens do corredor de celas. Cada porta tem um número riscado e um nome apagado com tinta. Uma etiqueta no chão diz: REALOCADO.",
                    "Na sala principal, uma plataforma escura no centro do piso. Alguém escreveu na parede: 'Felix Kraken sabe para onde eles foram.' O nome volta nos registros, sempre à margem, sempre depois que algo desaparece."
            },
            {
                    "A imagem fica escura sem aviso. Uma forma alta, parada no limite do enquadramento. Quando a câmera o encara por tempo demais, ele desaparece.",
                    "Quando a câmera desvia, ele está mais perto. Quem o viu relata o mesmo: silêncio, frio e a sensação de que alguém se posicionou logo atrás."
            },
            {
                    "Floresta de Saint Juana, fim de tarde. A trilha termina em uma clareira sem pássaros. A câmera avança sozinha, como se alguém a carregasse com pressa.",
                    "No centro da clareira, a terra foi revirada. Duas marcações de madeira, cada uma com um nome. O vento para exatamente quando a imagem foca nelas."
            },
            {
                    "A última fita. As marcações de madeira mostram os nomes: Edd e Molly. A terra ao redor está fria demais para a estação. A câmera treme.",
                    "Atrás da câmera, uma respiração. Depois, uma voz muito baixa: 'Eles nunca saíram da floresta. E agora você também não vai sair.' Estática. Fim da gravação."
            }
    };

    private TapeLore() {}

    private static int index(int id) {
        return Math.max(1, Math.min(COUNT, id)) - 1;
    }

    public static String title(int id) {
        return TITLES[index(id)];
    }

    public static String[] pages(int id) {
        return PAGES[index(id)];
    }
}
