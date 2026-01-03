package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.InterviewSchedule;
import com.example.demo.models.dto.InterviewScheduleDto;

@Repository
public interface InterviewScheduleRepository extends JpaRepository<InterviewSchedule, Integer> {
    @Query("""
                    SELECT
                        new com.example.demo.models.dto.InterviewScheduleDto(schedule.id, rdmp.id,user.id,job.name,user.firstName,user.lastName,schedule.interviewType,schedule.interviewDate,schedule.location, schedule.interviewStatus,schedule.createdAt)
                    FROM
                        InterviewSchedule schedule JOIN schedule.roadmap rdmp JOIN rdmp.candidateEmployee user JOIN rdmp.jobVacancy job
                    WHERE
                        schedule.createdAt = (
                            SELECT MAX(schedule2.createdAt)
                            FROM InterviewSchedule schedule2 JOIN schedule2.roadmap rdmp2
                            WHERE rdmp2.id = rdmp.id
                        )
            """)
    public List<InterviewScheduleDto> getAllData();

    @Query("""
                    SELECT
                        new com.example.demo.models.dto.InterviewScheduleDto(schedule.id,rdmp.id,user.id,job.name,user.firstName,user.lastName,schedule.interviewType,schedule.interviewDate,schedule.location, schedule.interviewStatus,schedule.createdAt)
                    FROM
                        InterviewSchedule schedule JOIN schedule.roadmap rdmp JOIN rdmp.candidateEmployee user JOIN rdmp.jobVacancy job
                    WHERE
                        rdmp.id = ?1
            """)
    public List<InterviewScheduleDto> getDetailByJobRoadmap(Integer roadmap);

    @Query("""
                    SELECT
                        new com.example.demo.models.dto.InterviewScheduleDto(schedule.id,rdmp.id,u.id,job.name,c.firstName,c.lastName,schedule.interviewType,schedule.interviewDate,schedule.location, schedule.interviewStatus,schedule.createdAt)
                    FROM
                        InterviewSchedule schedule JOIN schedule.roadmap rdmp JOIN schedule.interviewer u JOIN u.candidateEmployee c JOIN rdmp.jobVacancy job
                    WHERE
                        schedule.interviewer.id = ?1 AND schedule.interviewStatus IN ('SCHEDULED','RESCHEDULED') AND schedule.createdAt = (
                        SELECT MAX(schedule2.createdAt)
                        FROM InterviewSchedule schedule2
                        WHERE schedule2.roadmap.id = rdmp.id 
                        AND schedule2.interviewStatus IN ('SCHEDULED','RESCHEDULED')
                        ) 
                    ORDER BY schedule.createdAt DESC
            """)
    public List<InterviewScheduleDto> getAllDataByInterviewer(Integer interviewer);

    @Query("""
                    SELECT
                        new com.example.demo.models.dto.InterviewScheduleDto(schedule.id,rdmp.id,user.id,job.name,user.firstName,user.lastName,schedule.interviewType,schedule.interviewDate,schedule.location, schedule.interviewStatus,schedule.createdAt)
                    FROM
                        InterviewSchedule schedule JOIN schedule.roadmap rdmp JOIN rdmp.candidateEmployee user JOIN rdmp.jobVacancy job
                    WHERE
                        schedule.id = ?1
            """)
    public InterviewScheduleDto getAllDataById(Integer id);
}
