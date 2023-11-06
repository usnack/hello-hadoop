from mrjob.job import MRJob
from mrjob.step import MRStep

class RatingsBreakdown(MRJob):
    def steps(self):
        return [
            MRStep(mapper=self.mapper_get_movieIDs,
                   reducer=self.reducer_count_ratings),
            MRStep(reducer=self.reducer_sort_by_value),
        ]

    def mapper_get_ratings(self, _, line):
        (userID, movieID, rating, timestamp) = line.split('\t')
        yield rating, 1

    def mapper_get_movieIDs(self, _, line):
        (userID, movieID, rating, timestamp) = line.split('\t')
        yield movieID, 1

    def reducer_count_ratings(self, movieID, number_ones):
        yield str(sum(number_ones)).zfill(5), movieID

    def reducer_sort_by_value(self, rating, movies):
        for movie in movies:
            yield movie, rating



if __name__ == '__main__':
    RatingsBreakdown.run()