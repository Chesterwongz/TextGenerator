# Text Generator using Markov Chains

Writing-intensive modules can be hard: so many 10-page essays, and not nearly enough time to
catch up on the latest e-lectures. Say no more! This automatic writing program can easily produce pages and pages of new
text. And it will adapt to your chosen style. If you use an old essay as input, your new essay will
sound just like it was written by you! If you use Shakespeare as input, your new essay will sound
as if it was written by the Bard himself.
The basic idea is to take an input text and calculate its statistical properties. For example, given
a specific string “prope”, what is the probability that the next letter is an ‘r’? What is the
probability that the next letter is a ‘q’? The program will take a text as input, calculate this
statistical information, and then use it to produce a reasonable output text.
Claude Shannon first suggested this technique in a seminal paper A Mathematical Theory of Communication (1948). 
This paper contained many revolutionary ideas, but one of them was to use
a Markov Chain to model the statistical properties of a particular text. Markov Chains are now
used everywhere; for example, the Google PageRank algorithm is built on ideas related to Markov
Chains.

## Markov Models

Given a text, you can build up the Markov Model. A Markov Model captures the frequency of
a specific letter/character appearing after a specific preceding string (which can be of varying
length). The order of the Markov model is the length of that preceding string.
For example, if we have the following text:
a b d a c a b d a c b d a b d a c d a
We can build the following Markov Model of order 1:
|---|---|-----|
| a <br /> a | b <br /> c | 1/2 <br /> 1/2 |
| b | d | 1   |
| c <br /> c <br /> c | a <br /> b <br /> d | 1/3 <br /> 1/3 <br /> 1/3 |
| d | a | 1   |
This implies the following:
• After the string ‘a’, half the time you find a ‘b’, and half the time you find a ‘c’.
2
• After the string ‘b’, you always find a ‘d’.
• After the string ‘c’, one-third of the time you find letters ‘a’, ‘b’, or ‘d’ respectively (i.e., they
are equally common after a ’c’).
• After the string ‘d’, you always find an ‘a’.
You can think of these as probabilities (though so far, there is no randomness at all). Notice that
in the text above the table, there are three instances when the character ‘a’ is followed by a ‘b’, and
there are three instances when ‘a’ is followed by a ‘c’. Similarly, ‘b’ is always followed by a ‘d’, and
‘d’ is always followed by an ‘a’. The character ‘c’ is followed by an ‘a’ once, a ‘b’ once, and a ‘d’ once.
A Markov Model of order 2 captures how likely a given letter is to follow a string of length 2.
Suppose we have the following text:
a b c d a b d d a b c d d a b d
Here we have an example of Markov Model of order 2 built by the text above:
ab c 1/2
ab d 1/2
bc d 1
bd d 1
cd a 1/2
cd d 1/2
da b 1
dd a 1
Notice that in the text above, there are two instances when the string ’ab’ is followed by the letter
‘c’ and two instances when the string ’ab’ is followed by the letter ‘d’. After the string ‘bc’, you
always get the letter ‘d’, and after the string ‘bd’, you always get the letter ‘d’, etc.
Producing a New Text
Once you have your Markov Model, you can go about generating a new text. You need to start
with a seed string of the same length as the order of the Markov Model. For example, if the Markov
Model is of order 6, you need to start with a string of length 6.
We use the term ‘kgrams’ to refer to the k-character strings, where k is the order. In
order to generate the next character, you look back at the previous k characters (inclusive of the
current last character). Look up that kgram in your Markov Model, and find the frequency that
each character appears after that kgram. If the kgram never appeared in your Markov Model, then
your newly-generated text is completed. Otherwise, you randomly choose the next character based
on the probability distribution indicated by the Markov Model.
3
Once you have found the next character that way, you add it to the end of your string, and repeat
the process as many times as you want!

The .txt files such as Alice.txt, hamlet.txt, etc and Test.in are used for you to try the TextGenerator class.