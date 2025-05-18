<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Calculate Grade</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container py-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Grade Calculator</h2>
            <a href="dashboard.jsp" class="btn btn-outline-secondary">Back to Dashboard</a>
        </div>
        
        <div class="card shadow">
            <div class="card-header bg-info text-white">
                <h4 class="mb-0">Enter Your Marks</h4>
            </div>
            <div class="card-body">
                <form action="CalculationServlet.do" method="post">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Assignment (marks)</label>
                            <input type="number" class="form-control" name="assignment" 
                                   min="0" max="100" step="0.01" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Industry Project (marks)</label>
                            <input type="number" class="form-control" name="project" 
                                   min="0" max="100" step="0.01" required>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Class Tests (marks)</label>
                            <input type="number" class="form-control" name="classTest" 
                                   min="0" max="100" step="0.01" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Semester Test (marks)</label>
                            <input type="number" class="form-control" name="semesterTest" 
                                   min="0" max="100" step="0.01" required>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Quizzes (marks)</label>
                            <input type="number" class="form-control" name="quiz" 
                                   min="0" max="100" step="0.01" required>
                        </div>
                    </div>
                    
                    <div class="text-center mt-4">
                        <button type="submit" class="btn btn-primary btn-lg">Calculate Final Grade</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>