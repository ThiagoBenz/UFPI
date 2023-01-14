/*
Autor: Thiago Oliveira
*/

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define QUANTIDADE_DE_FILOSOFOS 5
#define NAMEMAX_FILOSOFO 256

typedef pthread_mutex_t T_Garfo;
#define ESTADO_PENSANDO 0
#define ESTADO_FAMINTO 1
#define ESTADO_COMENDO 2

pthread_mutex_t mutex_global_fala;
T_Garfo garfos[QUANTIDADE_DE_FILOSOFOS];

typedef struct
{
    int nomeid;
    T_Garfo* garfoEsquerda;
    T_Garfo* garfoDireita;
    int estado;
    pthread_t thread;
} T_Filosofo;

char *nomes[QUANTIDADE_DE_FILOSOFOS] = {
    "Jefferson Caminhões (1)",
    "Jailson Mendes (2)",
    "Isaac Newton (3)",
    "Aristóteles (4)",
    "Platão (5)",
};

char *nome_filosofo(int id)
{
    return nomes[id];
}

T_Filosofo criarFilosofo(int id, T_Garfo garfos[])
{
    T_Filosofo filosofo;

    filosofo.nomeid = id;
    filosofo.estado = 0;
    filosofo.garfoEsquerda = &garfos[id];
    filosofo.garfoDireita = &garfos[(id + 1) % QUANTIDADE_DE_FILOSOFOS];
    return filosofo;
}

void falar_besteira_qualquer(int nomeid_filosofo)
{
    char besteiras[5][256] = {
        "Penso, logo existo",
        "o que sabemos é uma gota, o que ignoramos é um oceano",
        "Três pratos de trigo para três tigres tristes",
        "Um animal sem chifres é um animal indefeso",
        "Se eu tenho dois quilos de carne, dá pra 20 comer",
    };
    int i = rand() % 5;
    pthread_mutex_lock(&mutex_global_fala);
    printf("%s:\n-> %s\n", nome_filosofo(nomeid_filosofo), besteiras[i]);
    pthread_mutex_unlock(&mutex_global_fala);
}

void inform(char* text) {
    pthread_mutex_lock(&mutex_global_fala);
    printf("%s\n", text);
    pthread_mutex_unlock(&mutex_global_fala);
}

// Criar função para filosofar
void *filosofar(void *_filosofo)
{
    T_Filosofo *filosofo = (T_Filosofo *)_filosofo;
    int *estado = &(filosofo->estado);

    while (1)
    {
        switch (*estado)
        {
        case ESTADO_PENSANDO:
            // Está pensando
            pthread_mutex_lock(&mutex_global_fala);
            printf("%s está pensando\n", nome_filosofo(filosofo->nomeid));
            pthread_mutex_unlock(&mutex_global_fala);
            falar_besteira_qualquer(filosofo->nomeid);
            sleep(5);
            filosofo->estado = ESTADO_FAMINTO;
            break;
        case ESTADO_FAMINTO:
            // Está faminto
            pthread_mutex_lock(&mutex_global_fala);
            printf("%s está com fome\n", nome_filosofo(filosofo->nomeid));
            pthread_mutex_unlock(&mutex_global_fala);
            if (pthread_mutex_trylock(filosofo->garfoEsquerda) == 0)
            {
                if (pthread_mutex_trylock(filosofo->garfoDireita) == 0)
                {
                    pthread_mutex_lock(&mutex_global_fala);
                    printf("%s pegou os garfos\n", nome_filosofo(filosofo->nomeid));
                    pthread_mutex_unlock(&mutex_global_fala);
                    filosofo->estado = ESTADO_COMENDO;
                }
                else
                {
                    pthread_mutex_lock(&mutex_global_fala);
                    printf("%s não conseguiu pegar o garfo direito, então soltou o garfo esquerdo também\n", nome_filosofo(filosofo->nomeid));
                    pthread_mutex_unlock(&mutex_global_fala);
                    pthread_mutex_unlock(filosofo->garfoEsquerda);
                }
            }
            sleep(5);
            break;
        case ESTADO_COMENDO:
            // Está comendo
            pthread_mutex_lock(&mutex_global_fala);
            printf("%s está comendo\n", nome_filosofo(filosofo->nomeid));
            pthread_mutex_unlock(&mutex_global_fala);
            sleep(5);
            filosofo->estado = ESTADO_PENSANDO;
            pthread_mutex_unlock(filosofo->garfoEsquerda);
            pthread_mutex_unlock(filosofo->garfoDireita);
            pthread_mutex_lock(&mutex_global_fala);
            printf("%s terminou de ir ali comer\n", nome_filosofo(filosofo->nomeid));
            pthread_mutex_unlock(&mutex_global_fala);
            break;
        }
    }
}

void historia() {
    pthread_mutex_lock(&mutex_global_fala);
    printf("Na mesa a seguir, se encontra os seguintes filósofos contemporâneos:\n");
    for (int i = 0; i < QUANTIDADE_DE_FILOSOFOS; i++) {
        printf("- %s\n", nome_filosofo(i));
    }
    printf("Todos estão formando um círculo, onde %s está sentado ao lado de %s.\n",
        nome_filosofo(0),
        nome_filosofo(QUANTIDADE_DE_FILOSOFOS - 1)
    );
    printf("Todos pegam suas bebidas, pratos e talheres, e começam a falar suas \"fisolofadas\".\n");
    pthread_mutex_unlock(&mutex_global_fala);
}

int main()
{
    T_Filosofo filosofos[QUANTIDADE_DE_FILOSOFOS];
    pthread_mutex_init(&mutex_global_fala, NULL);
    historia();
    for (int i = 0; i < QUANTIDADE_DE_FILOSOFOS; i++)
    {
        pthread_mutex_init(&garfos[i], NULL);
        filosofos[i] = criarFilosofo(i, garfos);
    }
    for (int i = 0; i < QUANTIDADE_DE_FILOSOFOS; i++)
    {
        pthread_create(&filosofos[i].thread, NULL, &filosofar, &filosofos[i]);
    }
    for (int i = 0; i < QUANTIDADE_DE_FILOSOFOS; i++)
    {
        pthread_join(filosofos[i].thread, NULL);
    }
    while (1)
    {
        sleep(1);
    }
    return 0;
}