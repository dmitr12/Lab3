import pandas as pd
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans


def funcKMean(data, colorMap, numberClusters):
    df = pd.DataFrame(data)
    df = df[df[data.keys()[1]] != 0]
    kmeans = KMeans(n_clusters=numberClusters)
    kmeans.fit(df)
    labels = kmeans.predict(df)
    centroids = kmeans.cluster_centers_
    fig = plt.figure(figsize=(6, 6))
    colors_map = map(lambda x: colorMap[x + 1], labels)
    colors = list(colors_map)
    plt.scatter(df[data.keys()[1]], df[data.keys()[0]], color=colors, alpha=0.5, edgecolor='k')
    for idx, centroid in enumerate(centroids):
        plt.scatter(*centroid, color=colorMap[idx + 1])
    plt.xlim(-10, 200)
    plt.ylim(0, 50000)
    plt.xlabel(data.keys()[1])
    plt.ylabel("Count " + data.keys()[0])
    plt.show(block=False)
def main():
    df_raw = pd.read_json(r"data.json")
    colmap = {1: 'red', 2: 'green', 3: 'blue', 4: 'yellow', 5: 'black', 6: 'orange', 7: 'purple'}

    #Photo/age
    funcKMean(data = df_raw[['photo', 'age']], colorMap=colmap, numberClusters=3)
    funcKMean(data = df_raw[['photo', 'age']], colorMap=colmap, numberClusters=5)
    funcKMean(data = df_raw[['photo', 'age']], colorMap=colmap, numberClusters=7)

    #Video/age
    funcKMean(data=df_raw[['countVideo', 'age']], colorMap=colmap, numberClusters=3)
    funcKMean(data=df_raw[['countVideo', 'age']], colorMap=colmap, numberClusters=5)
    funcKMean(data=df_raw[['countVideo', 'age']], colorMap=colmap, numberClusters=7)

    #Note/age
    funcKMean(data=df_raw[['countNotes', 'age']], colorMap=colmap, numberClusters=3)
    funcKMean(data=df_raw[['countNotes', 'age']], colorMap=colmap, numberClusters=5)
    funcKMean(data=df_raw[['countNotes', 'age']], colorMap=colmap, numberClusters=7)

    #Group/age
    funcKMean(data=df_raw[['countGroups', 'age']], colorMap=colmap, numberClusters=3)
    funcKMean(data=df_raw[['countGroups', 'age']], colorMap=colmap, numberClusters=5)
    funcKMean(data=df_raw[['countGroups', 'age']], colorMap=colmap, numberClusters=7)

    plt.show()


if __name__ == '__main__':
    main()
