import cv2

img=cv2.imread("galaxy.jpg", 0) # get gray scale image

print(type(img))
print(img)

cv2.imshow("galaxy", img) # show image
cv2.waitKey(3000) # exit window after 8 seconds
cv2.destroyAllWindows()
