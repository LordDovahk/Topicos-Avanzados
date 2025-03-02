from textblob import TextBlob
import nltk

def contar_palabras(texto):
    palabras = nltk.word_tokenize(texto)
    return len(palabras)

def analizar_sentimiento(texto):
    analisis = TextBlob(texto)
    if analisis.sentiment.polarity > 0:
        return "Positivo"
    elif analisis.sentiment.polarity <= -1:
        return "Negativo"
    else:
        return "Neutral"

def main():
    texto = input("Ingresa un texto: ")
    print(f"Número de palabras: {contar_palabras(texto)}")
    print(f"Sentimiento: {analizar_sentimiento(texto)}")

if __name__ == "__main__":
    main()