# Plano de Implementação - Entrega 1 (E1)

Este plano visa concluir todos os requisitos da **Entrega 1** do projeto "Rastreamento de Veículos", garantindo a conformidade com o manual do curso.

## User Review Required

> [!IMPORTANT]
> A PoC (Proof of Concept) será criada diretamente na pasta `pocs/e1-auth/`. Como o projeto principal já possui o login funcionando, a PoC será uma versão mínima e isolada apenas para demonstrar o uso do Firebase Auth, conforme exigido.

## Propostas de Mudanças

### Documentação e Configuração

#### [MODIFY] [README.md](file:///C:/Users/mathe/Documents/ProjetoDM2/DM2_Rastreamento_Veiculo/README.md)
*   Adicionar a identificação do aluno e a opção de projeto escolhida ("Rastreamento de Veículos").

#### [MODIFY] [.gitignore](file:///C:/Users/mathe/Documents/ProjetoDM2/DM2_Rastreamento_Veiculo/.gitignore)
*   Garantir que as pastas de build e configurações locais (`.gradle/`, `build/`, `.idea/`, `.iml`) sejam ignoradas em todos os subprojetos (app e pocs).

### Prova de Conceito (PoC)

#### [NEW] [pocs/e1-auth/](file:///C:/Users/mathe/Documents/ProjetoDM2/DM2_Rastreamento_Veiculo/pocs/e1-auth/)
*   Criar uma estrutura mínima de projeto Android que utilize o Firebase Authentication para validar o conceito.
*   *Nota:* Como criar um projeto Android completo via código é extenso, focaremos nos arquivos essenciais (`build.gradle`, `AndroidManifest.xml` e a `MainActivity.kt` simplificada).

## Plano de Verificação

### Verificação Manual
1.  Validar se a estrutura de pastas após a criação da PoC corresponde ao manual.
2.  Verificar se o `README.md` atende aos requisitos de identificação.
3.  Simular o deploy da `app/` no emulador para garantir que o login continua funcional (já validado anteriormente, mas faremos um check final).

### Requisitos Git
*   Preparar a lista de arquivos para o commit da E1.
