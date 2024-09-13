INSERT INTO relic (relic_number, name, effect, rarity, rate) VALUES
                                                                 (1, '노을 사진', '하늘색, 노란색 블록 터트릴 때마다 +1 line score', 'normal', NULL),
                                                                 (2, 'T블록', '보라색 블록 터트릴 때마다 +1.5 line score', 'normal', NULL),
                                                                 (3, '보물 상자', '싱글, 더블에 유물 개수 만큼 +line score를 더함', 'normal', NULL),
                                                                 (4, '리롤 블록', '리롤 비용 1$ 감소', 'normal', NULL),
                                                                 (5, '재활용 블록', '해당 유물을 획득할 시 무작위 블록 3개 추가', 'normal', NULL),
                                                                 (6, '돈 주머니', '라인을 터트릴 때마다 머니 레벨만큼 line score +1추가', 'normal', NULL),
                                                                 (7, '초과 업무', '초과 점수 1000점 당 +1$', 'normal', NULL),
                                                                 (8, '금 블록', '가지고 있는 10$당 싱글 달성 시 +2 line score 점', 'normal', NULL),
                                                                 (9, '은색 반지', '스테이지에 실패할 경우 재도전', 'normal', NULL),
                                                                 (10, '쿠폰 블록', '매 상점을 들릴 때마다 리롤 1회 무료', 'normal', NULL),
                                                                 (11, '블록 망치', '3회 블록 추가 건너띄기', 'normal', NULL),
                                                                 (12, '딸기', '연두색, 빨간색 블록 터트릴 때마다 +1 line score', 'normal', NULL),
                                                                 (13, '썩은 귤', '파란색, 귤색 블록 터트릴 때마다 +1 line score', 'normal', NULL),
                                                                 (14, '트리플 블록', '트리플 +20 line score', 'normal', NULL),
                                                                 (15, '테트라 블록', '테트리스 +40 line score', 'normal', NULL),
                                                                 (16, '화이트 블록', '모든 블록을 모든 색깔로 취급', 'rare', NULL),
                                                                 (17, '다이아몬드', '가지고 있는 15$당 라인 클리어 시 +1 combo score점', 'rare', NULL),
                                                                 (18, '수표 블록', '가지고 있는 돈의 두 배 획득(최대 30$)', 'rare', NULL),
                                                                 (19, '흰색 해골', '스테이지의 25%만 달성해도 다음 스테이지로 통과, 이후에 블록 파괴(파괴됨)', 'rare', NULL),
                                                                 (20, '투자 블록', '스테이지가 끝날 때마다 가지고 있는 4$ 당 +1$(최대 10$)', 'rare', NULL),
                                                                 (21, '클론', '가지고 있는 무작위 유물의 능력을 복사', 'rare', NULL),
                                                                 (22, '심플 볼록', '싱글로 줄을 없앨 때마다, +5 combo score', 'rare', NULL),
                                                                 (23, '콤보 블록', '콤보 최대 1로 제한, 1콤보 시 +3 combo score', 'rare', NULL),
                                                                 (24, '먹보 블록', '최종 콤보 점수에 x1 배(블록 추가할 때마다 +0.1계수)', 'rare', 1.0),
                                                                 (25, '파이브 블록', 'line score 500점 달성할 때마다 combo score x1.5', 'rare', NULL),
                                                                 (26, '블랭크 블록', '한 줄에 9 칸만 채워져도 터짐', 'unique', NULL),
                                                                 (27, '별', '라인을 터트릴 때마다 +2 combo score', 'unique', NULL),
                                                                 (28, '파괴 블록', '블록을 놓을 때마다 +7 line score (스테이지가 끝날 때마다 무작위 유물 파괴한 후 +2 증가)', 'unique', 7.0),
                                                                 (29, '우산 블록', '블록을 7번째 놓을 때마다 밑에서 블록이 올라옴', 'unique', NULL),
                                                                 (30, '컨테이너 블록', '트리플을 할 때마다 combo score에 x1.25 배', 'unique', NULL);