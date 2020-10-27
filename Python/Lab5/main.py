import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from sklearn import linear_model
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression


def main():
    df_raw = pd.read_json(r"data.json")
    data = df_raw[['age', 'countVideo']]

    x = np.array(data['age']).reshape(-1, 1)
    y = np.array(data['countVideo'])

    model=LinearRegression()

    model.fit(x,y)

    r_sq=model.score(x,y)
    print(r_sq)

    y_pred=model.predict(x)

    plt.scatter(x,y,color='b')
    plt.plot(x,y_pred,color='r')
    plt.show()


if __name__ == '__main__':
    main()

