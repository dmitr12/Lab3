import numpy as np
import pandas as pnd
import matplotlib.pyplot as plt
import seaborn as sbrn
import sklearn as skl
import pickle

a=np.array([[1,2,3], [4,5,6]], dtype=int)
x=np.linspace(1,5,10)
p=pnd.Series([1,2,3,4,5],index=['a','b','c','d','e'], dtype=int)
print(a)
print(p)
x=np.linspace(-10,10,50);
y=x*x;
plt.title("График")
plt.xlabel("x")
plt.ylabel("y")
plt.plot(x,y,'r--')
plt.grid()
plt.show()