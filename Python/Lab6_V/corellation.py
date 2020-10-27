import pandas as pd
import numpy as np
from sklearn import linear_model
from sklearn.model_selection import train_test_split

json_data = pd.read_json(r"data.json")

data = json_data[['age', 'photo']]


def get_correlation():
    x = np.array(data['age']).reshape(-1, 1)
    y = np.array(data['photo']).reshape(-1, 1)
    x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.33)
    coff = linear_model.LinearRegression()
    coff.fit(x_train, y_train)
    print(coff.score(x_test, y_test))


if __name__ == '__main__':
    get_correlation()
