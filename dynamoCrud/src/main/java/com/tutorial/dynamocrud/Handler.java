package com.tutorial.dynamocrud;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.ArrayList;
import java.util.List;

public class Handler implements RequestHandler <Request, Object>{
    @Override
    public Object handleRequest(Request request, Context context) {

        AmazonDynamoDB db = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(db);
        User user = null;

        switch (request.getHttpMethod()){
            case  "GET":
                if (request.getId() == 0){
                    List<User> users = new ArrayList<>();
                    users = mapper.scan(User.class, new DynamoDBScanExpression());
                    return users;
                } else{
                    user =mapper.load(User.class, request.getId());
                    return user;
                }

            case "POST":
                user = request.getUser();
                mapper.save(user);
                return user;

            case "DELETE":
                user =mapper.load(User.class, request.getId());
                mapper.delete(user);
                return user;
        }

        return null;
    }
}
