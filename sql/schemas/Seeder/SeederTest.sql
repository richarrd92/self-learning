
-- 1. Check all users
SELECT * FROM user_table;

-- 2. Check followers/following relationships
SELECT *
FROM followers_following ff
JOIN user_table u ON ff.follower_id = u.id
JOIN user_table f ON ff.following_id = f.id;

-- 3. Check all tweets
SELECT *
FROM tweet t
JOIN user_table u ON t.author = u.id;

-- 4. Check all hashtags
SELECT * FROM hashtag;

-- 5. Check tweet-hashtag relationships
SELECT *
FROM tweet_hashtags th
JOIN tweet t ON th.tweet_id = t.id
JOIN hashtag h ON th.hashtag_id = h.id;

-- 6. Check tweet likes
SELECT *
FROM user_likes ul
JOIN tweet t ON ul.tweet_id = t.id
JOIN user_table u ON ul.user_id = u.id;

-- 7. Check mentions
SELECT *
FROM user_mentions um
JOIN tweet t ON um.tweet_id = t.id
JOIN user_table u ON um.user_id = u.id;
