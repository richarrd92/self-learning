import cv2, time

video=cv2.VideoCapture(0) # capture video using first camera (main = 0)

# simulate video taping 
while True:
    check, frame = video.read() # first frame captured

    print(check)
    print(frame)

    grayScale=cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY) # convert image to gray scale
    # qtime.sleep(1)
    cv2.imshow("Capturing", grayScale)
    key=cv2.waitKey(1) # 0 - press specified key to close window or 1-n to wait

    if key==ord('q'):
        break


video.release()
cv2.destroyAllWindows()