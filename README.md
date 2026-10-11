# The Walten Files: Terror (mod fan-made, Forge 1.20.1)

Mod de terror inspirado no analog horror The Walten Files. Não oficial.

## Gerar o .jar sem instalar nada (GitHub Actions)
1. Crie uma conta gratuita em github.com e um repositório novo (pode ser privado).
2. Envie TODO o conteúdo desta pasta para o repositório (botão "Add file > Upload files"; arraste a pasta
   extraída, incluindo a pasta oculta `.github`).
3. Abra a aba **Actions**. O build "Compilar mod" roda sozinho (ou clique em "Run workflow").
4. Quando ficar verde (uns 5 a 10 minutos), clique no build e baixe **walten-files-mod-jar** no fim da página.
5. Extraia o zip: o arquivo `waltenfiles-1.0.0.jar` vai na pasta `mods` do Minecraft **Forge 1.20.1**
   (Forge 47.2.0 ou mais novo, Java 17).

## Como compilar no seu PC (alternativa)
Instale o JDK 17 e o Gradle 8.1.1 e rode `gradle build` nesta pasta. O .jar sai em `build/libs`.
Ou use o Forge MDK 1.20.1: troque a pasta `src` do MDK pela `src` deste projeto.

## O que tem
- **Estruturas** (geram no mundo): Restaurante Walten (planícies/floresta), Projeto K9 (deserto/savana/planície nevada/colinas)
  e Floresta de Saint Juana (taiga/dark forest, com as covas de Edd e Molly).
- **Mobs**: Jack, Rosemary, Sophie, Edd, Molly, Felix Kraken, Homem das Sombras.
- **Animatrônicos**: Bon, Sha, Boozoo, Banny (só acordam de noite; Banny e Felix congelam quando você olha).
- **Itens**: TV Retrô (clique com uma fita na mão) e 8 Fitas Cassete com a lore. Ovos de spawn na aba criativa.

## Testar rápido (modo criativo)
- `/locate structure waltenfiles:restaurante_walten` (ou `projeto_k9`, `floresta_saint_juana`)
- `/place structure waltenfiles:restaurante_walten` (gera na sua posição)
- `/summon waltenfiles:shadow_man`
- `/give @s waltenfiles:tv_retro` e `/give @s waltenfiles:fita_cassete_1`

## Personalizar
- Comportamento/dano/vida dos mobs: `registry/ModEntities.java` (perfis e atributos).
- Texto das fitas: `lore/TapeLore.java` (são textos-base genéricos, troque pela lore oficial).
- Texturas: `assets/waltenfiles/textures/entity/*.png` (placeholders em layout de skin 64x64, substitua pelas suas).
- Estruturas: `tools/gen_structures.py` regera os `.nbt`. Rode `python3 tools/gen_assets.py` e `python3 tools/gen_structures.py`
  (ajuste o caminho ROOT dentro dos scripts). Você também pode substituir os `.nbt` por construções suas feitas com Structure Block.
