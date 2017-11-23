# proto to java
file=$1
if [ -z $file ]
then
	echo "should input make file name"
	exit
fi
pbPath=$PROTOBUFPATH;
$PROTOBUFPATH/bin/protoc -I=. --java_out=../src/main/java $file
