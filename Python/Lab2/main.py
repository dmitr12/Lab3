import requests
from bs4 import BeautifulSoup
import re
import matplotlib.pyplot as plt


def get_html(url):
    response = requests.get(url)
    return response.text


def get_all_links(html, depth):
    soup = BeautifulSoup(html, 'lxml')
    links = list()
    for link in soup.find_all('a'):
        a = link.get('href')
        if(get_slash_count(a)==depth):
            if type(a) is str:
                if not a.startswith('http'):
                    a = 'https://coinmarketcap.com' + a
                links.append(a)
    return links

def get_slash_count(a):
    result = 0
    if(type(a) is str):
        for char in a:
            if char == '/':
                result += 1
    return result


def get_all_words(html):
    soup = BeautifulSoup(html, 'lxml')
    plain_text = soup.get_text(" ")
    return list(filter(lambda word: len(word) > 1, re.findall("[a-zA-Z]+", plain_text)))


def create_character_hist(stats):
    plt.figure(1)
    plt.xlabel('Length')
    plt.ylabel('Count')
    plt.title("Буквы")
    plt.bar(stats.keys(), stats.values(), color='r')


def create_word_hist(stats):
    plt.figure(2)
    plt.xlabel('Length')
    plt.ylabel('Count')
    plt.title("Слова")
    word_lens = list(map(lambda key: len(key), stats.keys()))
    plt.bar(word_lens, stats.values(), color='r')

def rec(links, words):
    words_count=0
    characters_count=0
    for link in links:
        html=get_html(link)
        wrd = get_all_words(html)
        words.extend(wrd)
    return words



def main():
    url = "https://coinmarketcap.com/all/views/all/"
    alphabet = 'abcdefghijklmnopqrstuvwxyz'
    depth=1
    html = get_html(url)
    all_links = get_all_links(html, depth)
    for i in all_links:
        print(i)
    words = get_all_words(html)
    nLinks=all_links
    for i in range(2):
        html = get_html(all_links[i])
        words.extend(get_all_words(html))
    characters_count = {c: (''.join(words)).lower().count(c) for c in alphabet}
    words_count = {word: words.count(word) for word in words}
    print(words_count)
    create_word_hist(words_count)
    create_character_hist(characters_count)
    plt.show()


if __name__ == '__main__':
    main()
